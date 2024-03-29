/**
 * ***************************************************************************** Turnstone Biologics
 * Confidential
 *
 * <p>2018 Turnstone Biologics All Rights Reserved.
 *
 * <p>This file is subject to the terms and conditions defined in file 'license.txt', which is part
 * of this source code package.
 *
 * <p>Contributors : Turnstone Biologics - General Release
 * ****************************************************************************
 */
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.powersystemstabilizerdynamics.controller.query;

import com.occulue.api.*;
import com.occulue.controller.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Implements Spring Controller query CQRS processing for entity PowerSystemStabilizerDynamics.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/PowerSystemStabilizerDynamics")
public class PowerSystemStabilizerDynamicsQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a PowerSystemStabilizerDynamics using a UUID
   *
   * @param UUID powerSystemStabilizerDynamicsId
   * @return PowerSystemStabilizerDynamics
   */
  @GetMapping("/load")
  public PowerSystemStabilizerDynamics load(
      @RequestParam(required = true) UUID powerSystemStabilizerDynamicsId) {
    PowerSystemStabilizerDynamics entity = null;

    try {
      entity =
          PowerSystemStabilizerDynamicsBusinessDelegate.getPowerSystemStabilizerDynamicsInstance()
              .getPowerSystemStabilizerDynamics(
                  new PowerSystemStabilizerDynamicsFetchOneSummary(
                      powerSystemStabilizerDynamicsId));
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "failed to load PowerSystemStabilizerDynamics using Id "
              + powerSystemStabilizerDynamicsId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all PowerSystemStabilizerDynamics business objects
   *
   * @return Set<PowerSystemStabilizerDynamics>
   */
  @GetMapping("/")
  public List<PowerSystemStabilizerDynamics> loadAll() {
    List<PowerSystemStabilizerDynamics> powerSystemStabilizerDynamicsList = null;

    try {
      // load the PowerSystemStabilizerDynamics
      powerSystemStabilizerDynamicsList =
          PowerSystemStabilizerDynamicsBusinessDelegate.getPowerSystemStabilizerDynamicsInstance()
              .getAllPowerSystemStabilizerDynamics();

      if (powerSystemStabilizerDynamicsList != null)
        LOGGER.log(Level.INFO, "successfully loaded all PowerSystemStabilizerDynamicss");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all PowerSystemStabilizerDynamicss ", exc);
      return null;
    }

    return powerSystemStabilizerDynamicsList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected PowerSystemStabilizerDynamics powerSystemStabilizerDynamics = null;
  private static final Logger LOGGER =
      Logger.getLogger(PowerSystemStabilizerDynamicsQueryRestController.class.getName());
}
