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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas.controller.query;

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
 * Implements Spring Controller query CQRS processing for entity SetPoint.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/SetPoint")
public class SetPointQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a SetPoint using a UUID
   *
   * @param UUID setPointId
   * @return SetPoint
   */
  @GetMapping("/load")
  public SetPoint load(@RequestParam(required = true) UUID setPointId) {
    SetPoint entity = null;

    try {
      entity =
          SetPointBusinessDelegate.getSetPointInstance()
              .getSetPoint(new SetPointFetchOneSummary(setPointId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load SetPoint using Id " + setPointId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all SetPoint business objects
   *
   * @return Set<SetPoint>
   */
  @GetMapping("/")
  public List<SetPoint> loadAll() {
    List<SetPoint> setPointList = null;

    try {
      // load the SetPoint
      setPointList = SetPointBusinessDelegate.getSetPointInstance().getAllSetPoint();

      if (setPointList != null) LOGGER.log(Level.INFO, "successfully loaded all SetPoints");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all SetPoints ", exc);
      return null;
    }

    return setPointList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected SetPoint setPoint = null;
  private static final Logger LOGGER =
      Logger.getLogger(SetPointQueryRestController.class.getName());
}
