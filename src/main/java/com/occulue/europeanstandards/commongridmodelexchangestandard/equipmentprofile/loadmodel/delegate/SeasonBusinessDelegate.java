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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.loadmodel.delegate;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.validator.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

/**
 * Season business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of Season related services in the case of a Season business related
 *       service failing.
 *   <li>Exposes a simpler, uniform Season interface to the business tier, making it easy for
 *       clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill Season business related
 *       services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class SeasonBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public SeasonBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * Season Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return SeasonBusinessDelegate
   */
  public static SeasonBusinessDelegate getSeasonInstance() {
    return (new SeasonBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createSeason(CreateSeasonCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getSeasonId() == null) command.setSeasonId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      SeasonValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateSeasonCommand - by convention the future return value for a create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateSeasonCommand of Season is " + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create Season - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateSeasonCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateSeason(UpdateSeasonCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      SeasonValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateSeasonCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save Season - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteSeasonCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteSeasonCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      SeasonValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteSeasonCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to delete Season using Id = " + command.getSeasonId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the Season via SeasonFetchOneSummary
   *
   * @param summary SeasonFetchOneSummary
   * @return SeasonFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public Season getSeason(SeasonFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("SeasonFetchOneSummary arg cannot be null");

    Season entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      SeasonValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a Season
      // --------------------------------------
      CompletableFuture<Season> futureEntity =
          queryGateway.query(
              new FindSeasonQuery(new LoadSeasonFilter(summary.getSeasonId())),
              ResponseTypes.instanceOf(Season.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg = "Unable to locate Season with id " + summary.getSeasonId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all Seasons
   *
   * @return List<Season>
   * @exception ProcessingException Thrown if any problems
   */
  public List<Season> getAllSeason() throws ProcessingException {
    List<Season> list = null;

    try {
      CompletableFuture<List<Season>> futureList =
          queryGateway.query(
              new FindAllSeasonQuery(), ResponseTypes.multipleInstancesOf(Season.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all Season";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign EndDate on Season
   *
   * @param command AssignEndDateToSeasonCommand
   * @exception ProcessingException
   */
  public void assignEndDate(AssignEndDateToSeasonCommand command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getSeasonId());

    MonthDayBusinessDelegate childDelegate = MonthDayBusinessDelegate.getMonthDayInstance();
    SeasonBusinessDelegate parentDelegate = SeasonBusinessDelegate.getSeasonInstance();
    UUID childId = command.getAssignment().getMonthDayId();
    MonthDay child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      SeasonValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get MonthDay using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign EndDate on Season
   *
   * @param command UnAssignEndDateFromSeasonCommand
   * @exception ProcessingException
   */
  public void unAssignEndDate(UnAssignEndDateFromSeasonCommand command) throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      SeasonValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign EndDate on Season";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign StartDate on Season
   *
   * @param command AssignStartDateToSeasonCommand
   * @exception ProcessingException
   */
  public void assignStartDate(AssignStartDateToSeasonCommand command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getSeasonId());

    MonthDayBusinessDelegate childDelegate = MonthDayBusinessDelegate.getMonthDayInstance();
    SeasonBusinessDelegate parentDelegate = SeasonBusinessDelegate.getSeasonInstance();
    UUID childId = command.getAssignment().getMonthDayId();
    MonthDay child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      SeasonValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get MonthDay using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign StartDate on Season
   *
   * @param command UnAssignStartDateFromSeasonCommand
   * @exception ProcessingException
   */
  public void unAssignStartDate(UnAssignStartDateFromSeasonCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      SeasonValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign StartDate on Season";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return Season
   */
  private Season load(UUID id) throws ProcessingException {
    season = SeasonBusinessDelegate.getSeasonInstance().getSeason(new SeasonFetchOneSummary(id));
    return season;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private Season season = null;
  private static final Logger LOGGER = Logger.getLogger(SeasonBusinessDelegate.class.getName());
}
