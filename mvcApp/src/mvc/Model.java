/*
 * Date: March 6th 2024
 * Name: Nhat Tran
 * Version: 0.1
 * Changes: Implementing Model.java
 *
 * Date: March 9th 2024
 * Name: Agrika Gupta
 * Version: 0.1
 * Changes: Serializable interface implementation,publisher extension */
package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {
    private static final long serialVersionUID = 1L;





    private String fileName = null;
    private Boolean unsavedChanges = false;
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public Boolean getUnsavedChanges() { return unsavedChanges; }
    public void setUnsavedChanges(Boolean unsavedChanges) {this.unsavedChanges = unsavedChanges; }

    public void changed() {
        unsavedChanges = true;
    }





    /*
     * 2 ways to implement New and Open: reuse the same
     * memory space as the old model, or create a new
     * model and reset panel and view pointers to it.
     * The copy method is used in the first scheme
     */
    public void copy(Model other) {
        this.fileName = other.fileName;
        this.unsavedChanges = other.unsavedChanges;
    }
}