package hu.ak_akademia.book4you;

import java.io.IOException;

import javafx.event.ActionEvent;

public interface SaveAndClearBottoms {

	void saveAddedDatas(ActionEvent event) throws IOException;

	void emptyTextField(ActionEvent event) throws IOException;

}
