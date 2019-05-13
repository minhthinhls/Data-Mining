/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Minh Thinh
 * @param <T>
 */
public interface FileHandler<T extends FileHandler> {

    public List getList();

    public void toArff(String toUrl) throws IOException;

    public T read(String url) throws FileNotFoundException;

}
