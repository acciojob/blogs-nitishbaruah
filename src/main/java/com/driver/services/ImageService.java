package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        Blog blog=blogRepository2.findById(blogId).get();
        List<Image> imagesList=blog.getImageList();
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        imagesList.add(image);
        blog.setImageList(imagesList);
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){
        Image image=imageRepository2.findById(id).get();
        Blog blog=image.getBlog();
        for(Image img:blog.getImageList()){
            if(img.equals(image)){
                blog.getImageList().remove(img);
                break;
            }
        }
        blogRepository2.save(blog);
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        String[] screen=screenDimensions.split("X");
        String[] imageDimension=image.getDimensions().split("X");
        int count=(Integer.parseInt(screen[0])/Integer.parseInt(imageDimension[0]))*(Integer.parseInt(screen[1])/Integer.parseInt(imageDimension[1]));

        return count;

    }
}
