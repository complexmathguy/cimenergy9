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
package com.occulue.europeanstandards.commongridmodelexchangestandard.statevariablesprofile.statevariables.delegate;

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
 * SvInjection business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of SvInjection related services in the case of a SvInjection
 *       business related service failing.
 *   <li>Exposes a simpler, uniform SvInjection interface to the business tier, making it easy for
 *       clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill SvInjection business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class SvInjectionBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public SvInjectionBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * SvInjection Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return SvInjectionBusinessDelegate
   */
  public static SvInjectionBusinessDelegate getSvInjectionInstance() {
    return (new SvInjectionBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createSvInjection(CreateSvInjectionCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getSvInjectionId() == null) command.setSvInjectionId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateSvInjectionCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateSvInjectionCommand of SvInjection is " + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create SvInjection - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateSvInjectionCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateSvInjection(UpdateSvInjectionCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateSvInjectionCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save SvInjection - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteSvInjectionCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteSvInjectionCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteSvInjectionCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to delete SvInjection using Id = " + command.getSvInjectionId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the SvInjection via SvInjectionFetchOneSummary
   *
   * @param summary SvInjectionFetchOneSummary
   * @return SvInjectionFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public SvInjection getSvInjection(SvInjectionFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("SvInjectionFetchOneSummary arg cannot be null");

    SvInjection entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a SvInjection
      // --------------------------------------
      CompletableFuture<SvInjection> futureEntity =
          queryGateway.query(
              new FindSvInjectionQuery(new LoadSvInjectionFilter(summary.getSvInjectionId())),
              ResponseTypes.instanceOf(SvInjection.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg = "Unable to locate SvInjection with id " + summary.getSvInjectionId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all SvInjections
   *
   * @return List<SvInjection>
   * @exception ProcessingException Thrown if any problems
   */
  public List<SvInjection> getAllSvInjection() throws ProcessingException {
    List<SvInjection> list = null;

    try {
      CompletableFuture<List<SvInjection>> futureList =
          queryGateway.query(
              new FindAllSvInjectionQuery(), ResponseTypes.multipleInstancesOf(SvInjection.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all SvInjection";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign PInjection on SvInjection
   *
   * @param command AssignPInjectionToSvInjectionCommand
   * @exception ProcessingException
   */
  public void assignPInjection(AssignPInjectionToSvInjectionCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getSvInjectionId());

    ActivePowerBusinessDelegate childDelegate =
        ActivePowerBusinessDelegate.getActivePowerInstance();
    SvInjectionBusinessDelegate parentDelegate =
        SvInjectionBusinessDelegate.getSvInjectionInstance();
    UUID childId = command.getAssignment().getActivePowerId();
    ActivePower child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get ActivePower using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign PInjection on SvInjection
   *
   * @param command UnAssignPInjectionFromSvInjectionCommand
   * @exception ProcessingException
   */
  public void unAssignPInjection(UnAssignPInjectionFromSvInjectionCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign PInjection on SvInjection";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign QInjection on SvInjection
   *
   * @param command AssignQInjectionToSvInjectionCommand
   * @exception ProcessingException
   */
  public void assignQInjection(AssignQInjectionToSvInjectionCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getSvInjectionId());

    ReactivePowerBusinessDelegate childDelegate =
        ReactivePowerBusinessDelegate.getReactivePowerInstance();
    SvInjectionBusinessDelegate parentDelegate =
        SvInjectionBusinessDelegate.getSvInjectionInstance();
    UUID childId = command.getAssignment().getReactivePowerId();
    ReactivePower child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get ReactivePower using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign QInjection on SvInjection
   *
   * @param command UnAssignQInjectionFromSvInjectionCommand
   * @exception ProcessingException
   */
  public void unAssignQInjection(UnAssignQInjectionFromSvInjectionCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign QInjection on SvInjection";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign SvInjection on SvInjection
   *
   * @param command AssignSvInjectionToSvInjectionCommand
   * @exception ProcessingException
   */
  public void assignSvInjection(AssignSvInjectionToSvInjectionCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getSvInjectionId());

    SvInjectionBusinessDelegate childDelegate =
        SvInjectionBusinessDelegate.getSvInjectionInstance();
    SvInjectionBusinessDelegate parentDelegate =
        SvInjectionBusinessDelegate.getSvInjectionInstance();
    UUID childId = command.getAssignment().getSvInjectionId();
    SvInjection child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get SvInjection using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign SvInjection on SvInjection
   *
   * @param command UnAssignSvInjectionFromSvInjectionCommand
   * @exception ProcessingException
   */
  public void unAssignSvInjection(UnAssignSvInjectionFromSvInjectionCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      SvInjectionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign SvInjection on SvInjection";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return SvInjection
   */
  private SvInjection load(UUID id) throws ProcessingException {
    svInjection =
        SvInjectionBusinessDelegate.getSvInjectionInstance()
            .getSvInjection(new SvInjectionFetchOneSummary(id));
    return svInjection;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private SvInjection svInjection = null;
  private static final Logger LOGGER =
      Logger.getLogger(SvInjectionBusinessDelegate.class.getName());
}
