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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires.controller.query;

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
 * Implements Spring Controller query CQRS processing for entity SwitchSchedule.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/SwitchSchedule")
public class SwitchScheduleQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a SwitchSchedule using a UUID
   *
   * @param UUID switchScheduleId
   * @return SwitchSchedule
   */
  @GetMapping("/load")
  public SwitchSchedule load(@RequestParam(required = true) UUID switchScheduleId) {
    SwitchSchedule entity = null;

    try {
      entity =
          SwitchScheduleBusinessDelegate.getSwitchScheduleInstance()
              .getSwitchSchedule(new SwitchScheduleFetchOneSummary(switchScheduleId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load SwitchSchedule using Id " + switchScheduleId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all SwitchSchedule business objects
   *
   * @return Set<SwitchSchedule>
   */
  @GetMapping("/")
  public List<SwitchSchedule> loadAll() {
    List<SwitchSchedule> switchScheduleList = null;

    try {
      // load the SwitchSchedule
      switchScheduleList =
          SwitchScheduleBusinessDelegate.getSwitchScheduleInstance().getAllSwitchSchedule();

      if (switchScheduleList != null)
        LOGGER.log(Level.INFO, "successfully loaded all SwitchSchedules");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all SwitchSchedules ", exc);
      return null;
    }

    return switchScheduleList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected SwitchSchedule switchSchedule = null;
  private static final Logger LOGGER =
      Logger.getLogger(SwitchScheduleQueryRestController.class.getName());
}
