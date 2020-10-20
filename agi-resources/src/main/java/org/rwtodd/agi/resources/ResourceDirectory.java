/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.agi.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Responsible for generating DirEntry objects for every resource in a game.
 *
 * @author rwtodd
 */
public interface ResourceDirectory {

    /**
     * Locate the sound resource identified by `number`, or return
     * DirEntry.NON_EXISTENT.
     *
     * @param number the requested resource number.
     * @return the DirEntry for the resource
     */
    DirEntry findSound(int number);

    /**
     * Return the number of sounds available in this resource directory.
     *
     * @return the number of sounds
     */
    int getSoundCount();

    /**
     * Locate the pic resource identified by `number`, or return
     * DirEntry.NON_EXISTENT.
     *
     * @param number the requested resource number.
     * @return the DirEntry for the resource
     */
    DirEntry findPic(int number);

    /**
     * Return the number of pics available in this resource directory.
     *
     * @return the number of pics
     */
    int getPicCount();

    /**
     * Locate the view resource identified by `number`, or return
     * DirEntry.NON_EXISTENT.
     *
     * @param number the requested resource number.
     * @return the DirEntry for the resource
     */
    DirEntry findView(int number);

    /**
     * Return the number of views available in this resource directory.
     *
     * @return the number of views
     */
    int getViewCount();

    /**
     * Locate the logic resource identified by `number`, or return
     * DirEntry.NON_EXISTENT.
     *
     * @param number the requested resource number.
     * @return the DirEntry for the resource
     */
    DirEntry findLogic(int number);

    /**
     * Return the number of logic scripts available in this resource directory.
     *
     * @return the number of logic scripts
     */
    int getLogicCount();

}
