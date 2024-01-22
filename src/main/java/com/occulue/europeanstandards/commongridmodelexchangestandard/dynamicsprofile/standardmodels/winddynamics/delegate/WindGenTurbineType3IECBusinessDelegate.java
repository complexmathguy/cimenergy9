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
 * WindGenTurbineType3IEC business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of WindGenTurbineType3IEC related services in the case of a
 *       WindGenTurbineType3IEC business related service failing.
 *   <li>Exposes a simpler, uniform WindGenTurbineType3IEC interface to the business tier, making it
 *       easy for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill WindGenTurbineType3IEC
 *       business related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class WindGenTurbineType3IECBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public WindGenTurbineType3IECBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * WindGenTurbineType3IEC Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return WindGenTurbineType3IECBusinessDelegate
   */
  public static WindGenTurbineType3IECBusinessDelegate getWindGenTurbineType3IECInstance() {
    return (new WindGenTurbineType3IECBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createWindGenTurbineType3IEC(
      CreateWindGenTurbineType3IECCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getWindGenTurbineType3IECId() == null)
        command.setWindGenTurbineType3IECId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateWindGenTurbineType3IECCommand - by convention the future return value for a
      // create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateWindGenTurbineType3IECCommand of WindGenTurbineType3IEC is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create WindGenTurbineType3IEC - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateWindGenTurbineType3IECCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateWindGenTurbineType3IEC(
      UpdateWindGenTurbineType3IECCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateWindGenTurbineType3IECCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save WindGenTurbineType3IEC - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteWindGenTurbineType3IECCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteWindGenTurbineType3IECCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteWindGenTurbineType3IECCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete WindGenTurbineType3IEC using Id = "
              + command.getWindGenTurbineType3IECId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the WindGenTurbineType3IEC via WindGenTurbineType3IECFetchOneSummary
   *
   * @param summary WindGenTurbineType3IECFetchOneSummary
   * @return WindGenTurbineType3IECFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public WindGenTurbineType3IEC getWindGenTurbineType3IEC(
      WindGenTurbineType3IECFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException(
          "WindGenTurbineType3IECFetchOneSummary arg cannot be null");

    WindGenTurbineType3IEC entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a WindGenTurbineType3IEC
      // --------------------------------------
      CompletableFuture<WindGenTurbineType3IEC> futureEntity =
          queryGateway.query(
              new FindWindGenTurbineType3IECQuery(
                  new LoadWindGenTurbineType3IECFilter(summary.getWindGenTurbineType3IECId())),
              ResponseTypes.instanceOf(WindGenTurbineType3IEC.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate WindGenTurbineType3IEC with id "
              + summary.getWindGenTurbineType3IECId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all WindGenTurbineType3IECs
   *
   * @return List<WindGenTurbineType3IEC>
   * @exception ProcessingException Thrown if any problems
   */
  public List<WindGenTurbineType3IEC> getAllWindGenTurbineType3IEC() throws ProcessingException {
    List<WindGenTurbineType3IEC> list = null;

    try {
      CompletableFuture<List<WindGenTurbineType3IEC>> futureList =
          queryGateway.query(
              new FindAllWindGenTurbineType3IECQuery(),
              ResponseTypes.multipleInstancesOf(WindGenTurbineType3IEC.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all WindGenTurbineType3IEC";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign Dipmax on WindGenTurbineType3IEC
   *
   * @param command AssignDipmaxToWindGenTurbineType3IECCommand
   * @exception ProcessingException
   */
  public void assignDipmax(AssignDipmaxToWindGenTurbineType3IECCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getWindGenTurbineType3IECId());

    PUBusinessDelegate childDelegate = PUBusinessDelegate.getPUInstance();
    WindGenTurbineType3IECBusinessDelegate parentDelegate =
        WindGenTurbineType3IECBusinessDelegate.getWindGenTurbineType3IECInstance();
    UUID childId = command.getAssignment().getPUId();
    PU child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get PU using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Dipmax on WindGenTurbineType3IEC
   *
   * @param command UnAssignDipmaxFromWindGenTurbineType3IECCommand
   * @exception ProcessingException
   */
  public void unAssignDipmax(UnAssignDipmaxFromWindGenTurbineType3IECCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Dipmax on WindGenTurbineType3IEC";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign Diqmax on WindGenTurbineType3IEC
   *
   * @param command AssignDiqmaxToWindGenTurbineType3IECCommand
   * @exception ProcessingException
   */
  public void assignDiqmax(AssignDiqmaxToWindGenTurbineType3IECCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getWindGenTurbineType3IECId());

    PUBusinessDelegate childDelegate = PUBusinessDelegate.getPUInstance();
    WindGenTurbineType3IECBusinessDelegate parentDelegate =
        WindGenTurbineType3IECBusinessDelegate.getWindGenTurbineType3IECInstance();
    UUID childId = command.getAssignment().getPUId();
    PU child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get PU using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Diqmax on WindGenTurbineType3IEC
   *
   * @param command UnAssignDiqmaxFromWindGenTurbineType3IECCommand
   * @exception ProcessingException
   */
  public void unAssignDiqmax(UnAssignDiqmaxFromWindGenTurbineType3IECCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Diqmax on WindGenTurbineType3IEC";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign WindGenTurbineType3IEC on WindGenTurbineType3IEC
   *
   * @param command AssignWindGenTurbineType3IECToWindGenTurbineType3IECCommand
   * @exception ProcessingException
   */
  public void assignWindGenTurbineType3IEC(
      AssignWindGenTurbineType3IECToWindGenTurbineType3IECCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getWindGenTurbineType3IECId());

    WindGenTurbineType3IECBusinessDelegate childDelegate =
        WindGenTurbineType3IECBusinessDelegate.getWindGenTurbineType3IECInstance();
    WindGenTurbineType3IECBusinessDelegate parentDelegate =
        WindGenTurbineType3IECBusinessDelegate.getWindGenTurbineType3IECInstance();
    UUID childId = command.getAssignment().getWindGenTurbineType3IECId();
    WindGenTurbineType3IEC child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get WindGenTurbineType3IEC using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign WindGenTurbineType3IEC on WindGenTurbineType3IEC
   *
   * @param command UnAssignWindGenTurbineType3IECFromWindGenTurbineType3IECCommand
   * @exception ProcessingException
   */
  public void unAssignWindGenTurbineType3IEC(
      UnAssignWindGenTurbineType3IECFromWindGenTurbineType3IECCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      WindGenTurbineType3IECValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign WindGenTurbineType3IEC on WindGenTurbineType3IEC";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return WindGenTurbineType3IEC
   */
  private WindGenTurbineType3IEC load(UUID id) throws ProcessingException {
    windGenTurbineType3IEC =
        WindGenTurbineType3IECBusinessDelegate.getWindGenTurbineType3IECInstance()
            .getWindGenTurbineType3IEC(new WindGenTurbineType3IECFetchOneSummary(id));
    return windGenTurbineType3IEC;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private WindGenTurbineType3IEC windGenTurbineType3IEC = null;
  private static final Logger LOGGER =
      Logger.getLogger(WindGenTurbineType3IECBusinessDelegate.class.getName());
}
