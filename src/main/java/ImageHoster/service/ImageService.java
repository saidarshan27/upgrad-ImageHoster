package ImageHoster.service;

import ImageHoster.model.Image;
import ImageHoster.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {

    private List<Image> images = new ArrayList<>();
    @Autowired
    private ImageRepository imageRepository;

    //Call the getAllImages() method in the Repository and obtain a List of all the images in the database
    public List<Image> getAllImages() {
        //Complete the method
        return imageRepository.getAllImages();
    }


    //The method calls the createImage() method in the Repository and passes the image to be persisted in the database
    public void uploadImage(Image image) {
        imageRepository.uploadImage(image);
    }


    //The method calls the getImageByTitle() method in the Repository and passes the title of the image to be fetched
    public Image getImageByTitle(String title) {
        //Complete the method
        Image foundImage = imageRepository.getImageByTitle(title);
        if(foundImage != null){
            return foundImage;
        }else{
            return null;
        }
    }

    //The method calls the getImage() method in the Repository and passes the id of the image to be fetched
    public Image getImage(Integer imageId) {
        //Complete the method
        return imageRepository.getImage(imageId);
    }

    //The method calls the updateImage() method in the Repository and passes the Image to be updated in the database
    public void updateImage(Image updatedImage) {
        //Complete the method
        imageRepository.updateImage(updatedImage);
    }

    public void deleteImage(Integer imageId){
        Image image = imageRepository.getImage(imageId);
        imageRepository.deleteImage(image);
    }
}
