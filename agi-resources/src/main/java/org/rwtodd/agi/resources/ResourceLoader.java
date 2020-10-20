/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.agi.resources;

/**
 * An interface describing classes that actually load resources and construct objects
 * of them.
 * @author rwtodd
 */
public interface ResourceLoader {
    void loadSound(int number) throws AGIException, ResourceNotPresentException;
    void loadSound(DirEntry de) throws AGIException, ResourceNotPresentException;
}
