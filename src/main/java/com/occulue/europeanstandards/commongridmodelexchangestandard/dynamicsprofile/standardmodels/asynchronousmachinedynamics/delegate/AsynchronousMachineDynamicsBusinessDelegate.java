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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.asynchronousmachinedynamics.delegate;

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
 * AsynchronousMachineDynamics business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of AsynchronousMachineDynamics related services in the case of a
 *       AsynchronousMachineDynamics business related service failing.
 *   <li>Exposes a simpler, uniform AsynchronousMachineDynamics interface to the business tier,
 *       making it easy for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill
 *       AsynchronousMachineDynamics business related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class AsynchronousMachineDynamicsBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public AsynchronousMachineDynamicsBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * AsynchronousMachineDynamics Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return AsynchronousMachineDynamicsBusinessDelegate
   */
  public static AsynchronousMachineDynamicsBusinessDelegate
      getAsynchronousMachineDynamicsInstance() {
    return (new AsynchronousMachineDynamicsBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createAsynchronousMachineDynamics(
      CreateAsynchronousMachineDynamicsCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getAsynchronousMachineDynamicsId() == null)
        command.setAsynchronousMachineDynamicsId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      AsynchronousMachineDynamicsValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateAsynchronousMachineDynamicsCommand - by convention the future return value
      // for a create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateAsynchronousMachineDynamicsCommand of AsynchronousMachineDynamics is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create AsynchronousMachineDynamics - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateAsynchronousMachineDynamicsCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateAsynchronousMachineDynamics(
      UpdateAsynchronousMachineDynamicsCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      AsynchronousMachineDynamicsValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateAsynchronousMachineDynamicsCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save AsynchronousMachineDynamics - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteAsynchronousMachineDynamicsCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteAsynchronousMachineDynamicsCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      AsynchronousMachineDynamicsValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteAsynchronousMachineDynamicsCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete AsynchronousMachineDynamics using Id = "
              + command.getAsynchronousMachineDynamicsId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the AsynchronousMachineDynamics via
   * AsynchronousMachineDynamicsFetchOneSummary
   *
   * @param summary AsynchronousMachineDynamicsFetchOneSummary
   * @return AsynchronousMachineDynamicsFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public AsynchronousMachineDynamics getAsynchronousMachineDynamics(
      AsynchronousMachineDynamicsFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException(
          "AsynchronousMachineDynamicsFetchOneSummary arg cannot be null");

    AsynchronousMachineDynamics entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      AsynchronousMachineDynamicsValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a AsynchronousMachineDynamics
      // --------------------------------------
      CompletableFuture<AsynchronousMachineDynamics> futureEntity =
          queryGateway.query(
              new FindAsynchronousMachineDynamicsQuery(
                  new LoadAsynchronousMachineDynamicsFilter(
                      summary.getAsynchronousMachineDynamicsId())),
              ResponseTypes.instanceOf(AsynchronousMachineDynamics.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate AsynchronousMachineDynamics with id "
              + summary.getAsynchronousMachineDynamicsId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all AsynchronousMachineDynamicss
   *
   * @return List<AsynchronousMachineDynamics>
   * @exception ProcessingException Thrown if any problems
   */
  public List<AsynchronousMachineDynamics> getAllAsynchronousMachineDynamics()
      throws ProcessingException {
    List<AsynchronousMachineDynamics> list = null;

    try {
      CompletableFuture<List<AsynchronousMachineDynamics>> futureList =
          queryGateway.query(
              new FindAllAsynchronousMachineDynamicsQuery(),
              ResponseTypes.multipleInstancesOf(AsynchronousMachineDynamics.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all AsynchronousMachineDynamics";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign AsynchronousMachineDynamics on AsynchronousMachineDynamics
   *
   * @param command AssignAsynchronousMachineDynamicsToAsynchronousMachineDynamicsCommand
   * @exception ProcessingException
   */
  public void assignAsynchronousMachineDynamics(
      AssignAsynchronousMachineDynamicsToAsynchronousMachineDynamicsCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getAsynchronousMachineDynamicsId());

    AsynchronousMachineDynamicsBusinessDelegate childDelegate =
        AsynchronousMachineDynamicsBusinessDelegate.getAsynchronousMachineDynamicsInstance();
    AsynchronousMachineDynamicsBusinessDelegate parentDelegate =
        AsynchronousMachineDynamicsBusinessDelegate.getAsynchronousMachineDynamicsInstance();
    UUID childId = command.getAssignment().getAsynchronousMachineDynamicsId();
    AsynchronousMachineDynamics child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      AsynchronousMachineDynamicsValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get AsynchronousMachineDynamics using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign AsynchronousMachineDynamics on AsynchronousMachineDynamics
   *
   * @param command UnAssignAsynchronousMachineDynamicsFromAsynchronousMachineDynamicsCommand
   * @exception ProcessingException
   */
  public void unAssignAsynchronousMachineDynamics(
      UnAssignAsynchronousMachineDynamicsFromAsynchronousMachineDynamicsCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      AsynchronousMachineDynamicsValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg =
          "Failed to unassign AsynchronousMachineDynamics on AsynchronousMachineDynamics";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return AsynchronousMachineDynamics
   */
  private AsynchronousMachineDynamics load(UUID id) throws ProcessingException {
    asynchronousMachineDynamics =
        AsynchronousMachineDynamicsBusinessDelegate.getAsynchronousMachineDynamicsInstance()
            .getAsynchronousMachineDynamics(new AsynchronousMachineDynamicsFetchOneSummary(id));
    return asynchronousMachineDynamics;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private AsynchronousMachineDynamics asynchronousMachineDynamics = null;
  private static final Logger LOGGER =
      Logger.getLogger(AsynchronousMachineDynamicsBusinessDelegate.class.getName());
}
