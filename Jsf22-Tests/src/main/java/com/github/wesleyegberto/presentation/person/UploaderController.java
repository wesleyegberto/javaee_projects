package com.github.wesleyegberto.presentation.person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class UploaderController {

	private Part file;

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public void save() throws IOException {
		System.out.println("Saving");
		// We should save the file outside of war application, because after restart/redeploy all files will be deleted
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String absoluteDiskPath = servletContext.getRealPath("resources/uploads/");
		
		// Copy uploaded file to destination path
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = file.getInputStream();
			outputStream = new FileOutputStream(absoluteDiskPath);

			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File upload successfull!"));
		} catch (IOException ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File upload successfull!"));
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
}
