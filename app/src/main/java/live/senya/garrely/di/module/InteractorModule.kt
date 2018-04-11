package live.senya.garrely.di.module

import live.senya.garrely.model.interactor.photo.EditorChoisePhotoInteractor
import live.senya.garrely.model.interactor.photo.PhotoInteractor
import toothpick.config.Module

class InteractorModule : Module() {
    
    init {
        bind(PhotoInteractor::class.java).to(EditorChoisePhotoInteractor::class.java)
    }
}