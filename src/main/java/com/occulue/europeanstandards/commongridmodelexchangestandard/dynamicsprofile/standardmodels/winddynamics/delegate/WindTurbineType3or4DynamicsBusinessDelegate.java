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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.winddynamics.delegate;

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
 * WindTurbineType3or4Dynamics business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of WindTurbineType3or4Dynamics related services in the case of a
 *       WindTurbineType3or4Dynamics business related service failing.
 *   <li>Exposes a simpler, uniform WindTurbineType3or4Dynamics interface to the business tier,
 *       making it easy for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill
 *       WindTurbineType3or4Dynamics business related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class WindTurbineType3or4DynamicsBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public WindTurbineType3or4DynamicsBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * WindTurbineType3or4Dynamics Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return WindTurbineType3or4DynamicsBusinessDelegate
   */
  public static WindTurbineType3or4DynamicsBusinessDelegate
      getWindTurbineType3or4DynamicsInstance() {
    return (new WindTurbineType3or4DynamicsBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createWindTurbineType3or4Dynamics(
      CreateWindTurbineType3or4DynamicsCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getWindTurbineType3or4DynamicsId() == null)
        command.setWindTurbineType3or4DynamicsId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      WindTurbineType3or4DynamicsValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateWindTurbineType3or4DynamicsCommand - by convention the future return value
      // for a create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateWindTurbineType3or4DynamicsCommand of WindTurbineType3or4Dynamics is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create WindTurbineType3or4Dynamics - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateWindTurbineType3or4DynamicsCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateWindTurbineType3or4Dynamics(
      UpdateWindTurbineType3or4DynamicsCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      WindTurbineType3or4DynamicsValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateWindTurbineType3or4DynamicsCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save WindTurbineType3or4Dynamics - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteWindTurbineType3or4DynamicsCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteWindTurbineType3or4DynamicsCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      WindTurbineType3or4DynamicsValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteWindTurbineType3or4DynamicsCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete WindTurbineType3or4Dynamics using Id = "
              + command.getWindTurbineType3or4DynamicsId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the WindTurbineType3or4Dynamics via
   * WindTurbineType3or4DynamicsFetchOneSummary
   *
   * @param summary WindTurbineType3or4DynamicsFetchOneSummary
   * @return WindTurbineType3or4DynamicsFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public WindTurbineType3or4Dynamics getWindTurbineType3or4Dynamics(
      WindTurbineType3or4DynamicsFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException(
          "WindTurbineType3or4DynamicsFetchOneSummary arg cannot be null");

    WindTurbineType3or4Dynamics entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      WindTurbineType3or4DynamicsValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a WindTurbineType3or4Dynamics
      // --------------------------------------
      CompletableFuture<WindTurbineType3or4Dynamics> futureEntity =
          queryGateway.query(
              new FindWindTurbineType3or4DynamicsQuery(
                  new LoadWindTurbineType3or4DynamicsFilter(
                      summary.getWindTurbineType3or4DynamicsId())),
              ResponseTypes.instanceOf(WindTurbineType3or4Dynamics.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate WindTurbineType3or4Dynamics with id "
              + summary.getWindTurbineType3or4DynamicsId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all WindTurbineType3or4Dynamicss
   *
   * @return List<WindTurbineType3or4Dynamics>
   * @exception ProcessingException Thrown if any problems
   */
  public List<WindTurbineType3or4Dynamics> getAllWindTurbineType3or4Dynamics()
      throws ProcessingException {
    List<WindTurbineType3or4Dynamics> list = null;

    try {
      CompletableFuture<List<WindTurbineType3or4Dynamics>> futureList =
          queryGateway.query(
              new FindAllWindTurbineType3or4DynamicsQuery(),
              ResponseTypes.multipleInstancesOf(WindTurbineType3or4Dynamics.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all WindTurbineType3or4Dynamics";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign WindTurbineType3or4Dynamics on WindTurbineType3or4Dynamics
   *
   * @param command AssignWindTurbineType3or4DynamicsToWindTurbineType3or4DynamicsCommand
   * @exception ProcessingException
   */
  public void assignWindTurbineType3or4Dynamics(
      AssignWindTurbineType3or4DynamicsToWindTurbineType3or4DynamicsCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getWindTurbineType3or4DynamicsId());

    WindTurbineType3or4DynamicsBusinessDelegate childDelegate =
        WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance();
    WindTurbineType3or4DynamicsBusinessDelegate parentDelegate =
        WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance();
    UUID childId = command.getAssignment().getWindTurbineType3or4DynamicsId();
    WindTurbineType3or4Dynamics child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      WindTurbineType3or4DynamicsValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get WindTurbineType3or4Dynamics using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign WindTurbineType3or4Dynamics on WindTurbineType3or4Dynamics
   *
   * @param command UnAssignWindTurbineType3or4DynamicsFromWindTurbineType3or4DynamicsCommand
   * @exception ProcessingException
   */
  public void unAssignWindTurbineType3or4Dynamics(
      UnAssignWindTurbineType3or4DynamicsFromWindTurbineType3or4DynamicsCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      WindTurbineType3or4DynamicsValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg =
          "Failed to unassign WindTurbineType3or4Dynamics on WindTurbineType3or4Dynamics";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return WindTurbineType3or4Dynamics
   */
  private WindTurbineType3or4Dynamics load(UUID id) throws ProcessingException {
    windTurbineType3or4Dynamics =
        WindTurbineType3or4DynamicsBusinessDelegate.getWindTurbineType3or4DynamicsInstance()
            .getWindTurbineType3or4Dynamics(new WindTurbineType3or4DynamicsFetchOneSummary(id));
    return windTurbineType3or4Dynamics;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private WindTurbineType3or4Dynamics windTurbineType3or4Dynamics = null;
  private static final Logger LOGGER =
      Logger.getLogger(WindTurbineType3or4DynamicsBusinessDelegate.class.getName());
}
