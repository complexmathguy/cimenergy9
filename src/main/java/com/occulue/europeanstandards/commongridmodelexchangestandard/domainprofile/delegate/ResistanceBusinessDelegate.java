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
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.delegate;

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
 * Resistance business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of Resistance related services in the case of a Resistance business
 *       related service failing.
 *   <li>Exposes a simpler, uniform Resistance interface to the business tier, making it easy for
 *       clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill Resistance business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class ResistanceBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public ResistanceBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * Resistance Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return ResistanceBusinessDelegate
   */
  public static ResistanceBusinessDelegate getResistanceInstance() {
    return (new ResistanceBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createResistance(CreateResistanceCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getResistanceId() == null) command.setResistanceId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      ResistanceValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateResistanceCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateResistanceCommand of Resistance is " + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create Resistance - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateResistanceCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateResistance(UpdateResistanceCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      ResistanceValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateResistanceCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save Resistance - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteResistanceCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteResistanceCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ResistanceValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteResistanceCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to delete Resistance using Id = " + command.getResistanceId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the Resistance via ResistanceFetchOneSummary
   *
   * @param summary ResistanceFetchOneSummary
   * @return ResistanceFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public Resistance getResistance(ResistanceFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("ResistanceFetchOneSummary arg cannot be null");

    Resistance entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      ResistanceValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a Resistance
      // --------------------------------------
      CompletableFuture<Resistance> futureEntity =
          queryGateway.query(
              new FindResistanceQuery(new LoadResistanceFilter(summary.getResistanceId())),
              ResponseTypes.instanceOf(Resistance.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg = "Unable to locate Resistance with id " + summary.getResistanceId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all Resistances
   *
   * @return List<Resistance>
   * @exception ProcessingException Thrown if any problems
   */
  public List<Resistance> getAllResistance() throws ProcessingException {
    List<Resistance> list = null;

    try {
      CompletableFuture<List<Resistance>> futureList =
          queryGateway.query(
              new FindAllResistanceQuery(), ResponseTypes.multipleInstancesOf(Resistance.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all Resistance";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign Value on Resistance
   *
   * @param command AssignValueToResistanceCommand
   * @exception ProcessingException
   */
  public void assignValue(AssignValueToResistanceCommand command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getResistanceId());

    FloatProxyBusinessDelegate childDelegate = FloatProxyBusinessDelegate.getFloatProxyInstance();
    ResistanceBusinessDelegate parentDelegate = ResistanceBusinessDelegate.getResistanceInstance();
    UUID childId = command.getAssignment().getFloatProxyId();
    FloatProxy child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      ResistanceValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get FloatProxy using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Value on Resistance
   *
   * @param command UnAssignValueFromResistanceCommand
   * @exception ProcessingException
   */
  public void unAssignValue(UnAssignValueFromResistanceCommand command) throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ResistanceValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Value on Resistance";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return Resistance
   */
  private Resistance load(UUID id) throws ProcessingException {
    resistance =
        ResistanceBusinessDelegate.getResistanceInstance()
            .getResistance(new ResistanceFetchOneSummary(id));
    return resistance;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private Resistance resistance = null;
  private static final Logger LOGGER = Logger.getLogger(ResistanceBusinessDelegate.class.getName());
}
