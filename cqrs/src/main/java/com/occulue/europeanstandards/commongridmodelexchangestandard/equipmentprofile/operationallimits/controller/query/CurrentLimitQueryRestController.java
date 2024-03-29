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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.operationallimits.controller.query;

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
 * Implements Spring Controller query CQRS processing for entity CurrentLimit.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/CurrentLimit")
public class CurrentLimitQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a CurrentLimit using a UUID
   *
   * @param UUID currentLimitId
   * @return CurrentLimit
   */
  @GetMapping("/load")
  public CurrentLimit load(@RequestParam(required = true) UUID currentLimitId) {
    CurrentLimit entity = null;

    try {
      entity =
          CurrentLimitBusinessDelegate.getCurrentLimitInstance()
              .getCurrentLimit(new CurrentLimitFetchOneSummary(currentLimitId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load CurrentLimit using Id " + currentLimitId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all CurrentLimit business objects
   *
   * @return Set<CurrentLimit>
   */
  @GetMapping("/")
  public List<CurrentLimit> loadAll() {
    List<CurrentLimit> currentLimitList = null;

    try {
      // load the CurrentLimit
      currentLimitList =
          CurrentLimitBusinessDelegate.getCurrentLimitInstance().getAllCurrentLimit();

      if (currentLimitList != null) LOGGER.log(Level.INFO, "successfully loaded all CurrentLimits");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all CurrentLimits ", exc);
      return null;
    }

    return currentLimitList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected CurrentLimit currentLimit = null;
  private static final Logger LOGGER =
      Logger.getLogger(CurrentLimitQueryRestController.class.getName());
}
