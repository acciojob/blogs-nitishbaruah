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
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        String s=image.getDimensions();
        int x=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='X'){
                x=i;
                break;
            }
        }
        int imageDimension=Integer.valueOf(s.substring(0,x))*Integer.valueOf(s.substring(x));
        x=0;
        for(int i=0;i<screenDimensions.length();i++){
            if(screenDimensions.charAt(i)=='X'){
                x=i;
                break;
            }
        }
        int screenDimension=Integer.valueOf(screenDimensions.substring(0,x))*Integer.valueOf(screenDimensions.substring(x));

        return screenDimension/imageDimension;

    }
}
