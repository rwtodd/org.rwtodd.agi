/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.agi.resources;

import java.nio.file.Path;

/**
 * Responsible for generating DirEntry objects for every resource in a game.
 * @author rwtodd
 */
public interface ResourceDirectory {
    static ResourceDirectory create(Path gameDir, EngineVersion version) {
        return null;
    }
    
    DirEntry findSound(int number);
}
