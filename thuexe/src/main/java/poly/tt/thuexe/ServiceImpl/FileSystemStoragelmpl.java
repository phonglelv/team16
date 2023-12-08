package poly.tt.thuexe.ServiceImpl;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poly.tt.thuexe.Config.StorageProperties;
import poly.tt.thuexe.Exception.StorageException;
import poly.tt.thuexe.Exception.StorageFileNotFoumdException;

@Service
public class FileSystemStoragelmpl implements poly.tt.thuexe.Service.StorageService {
private final Path rootLocation;

@Override
public String getStoreFilename(MultipartFile file, String id) {
	String ext = FilenameUtils.getExtension(file.getOriginalFilename());
	return"p" + id+ "." + ext;
}

public FileSystemStoragelmpl(StorageProperties properties) {
	this.rootLocation = Paths.get(properties.getLocation()).toAbsolutePath().normalize();
}

@Override
public void store(MultipartFile  file, String storeFilename) {
	try {
		 if (file.isEmpty()) {
			 throw new StorageException("Failed to store empty file");
		}
		 Path destinationFile = this.rootLocation.resolve(Paths.get(storeFilename)).normalize().toAbsolutePath();
		 
		 if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
			 throw new StorageException("Cannot store file outside current directory");
			
		}
		 try(InputStream inputStream = file.getInputStream()){
			 Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
		 }
	} catch (Exception e) {
		throw new StorageException("Filed to  store file", e);
	}
}

@Override
public Resource loadAsResource(String filename) {
	try {
		Path file = load(filename);
		Resource resource = new UrlResource(file.toUri());
		if (resource.exists() || resource.isReadable()) {
              return resource;			
		}
		
		throw new StorageFileNotFoumdException("Could not read file:" + filename);
	} catch (Exception e) {
		throw new StorageFileNotFoumdException("Could not read file: "+ filename);
	}
}
 @Override
public Path load(String filename) {
	 return rootLocation.resolve(filename);
 }
 @Override
public void delete(String storedFilename) throws IOException {
	 Path destinationFile = rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
 Files.delete(destinationFile);
 }
 @Override
public void init() {
	 try {
		Files.createDirectories(rootLocation);
		System.out.println(rootLocation.toString());
	} catch (Exception e) {
	 throw new StorageException("Count not initialize storage", e);
	}
 }
}
