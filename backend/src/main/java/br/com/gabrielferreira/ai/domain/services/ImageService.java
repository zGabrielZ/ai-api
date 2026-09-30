package br.com.gabrielferreira.ai.domain.services;

import br.com.gabrielferreira.ai.domain.model.ImageDataModel;

public interface ImageService {

    ImageDataModel image(String message);
}
