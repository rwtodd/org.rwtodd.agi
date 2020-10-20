/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rwtodd.agi.resources;

import java.nio.file.Path;

/**
 * An abstract factory for a family of resource-loading components that will 
 * work together.  The {@link Engine} uses an instance of this factory to create
 * the parts it will use.
 * @author rwtodd
 */
public interface ResourceLoadingFactory {
    GameMetaData      createGameMetaData(Path gamePath) throws AGIException;
    ResourceDirectory createResourceDirectory(Engine engine) throws AGIException;
    VolumeManager     createVolumeManager(Engine engine) throws AGIException;
    ResourceLoader    createResourceLoader(Engine engine) throws AGIException;
}
