package com.chatop.api.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {

    private String imageFilename;
    private String imageType;
    private byte[] imageData;

}
