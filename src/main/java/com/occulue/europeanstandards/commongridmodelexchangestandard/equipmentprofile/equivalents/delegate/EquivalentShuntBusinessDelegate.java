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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.equivalents.delegate;

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
 * EquivalentShunt business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of EquivalentShunt related services in the case of a
 *       EquivalentShunt business related service failing.
 *   <li>Exposes a simpler, uniform EquivalentShunt interface to the business tier, making it easy
 *       for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill EquivalentShunt business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class EquivalentShuntBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public EquivalentShuntBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * EquivalentShunt Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return EquivalentShuntBusinessDelegate
   */
  public static EquivalentShuntBusinessDelegate getEquivalentShuntInstance() {
    return (new EquivalentShuntBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createEquivalentShunt(CreateEquivalentShuntCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getEquivalentShuntId() == null) command.setEquivalentShuntId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      EquivalentShuntValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateEquivalentShuntCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateEquivalentShuntCommand of EquivalentShunt is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create EquivalentShunt - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateEquivalentShuntCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateEquivalentShunt(UpdateEquivalentShuntCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      EquivalentShuntValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateEquivalentShuntCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save EquivalentShunt - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteEquivalentShuntCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteEquivalentShuntCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      EquivalentShuntValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteEquivalentShuntCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete EquivalentShunt using Id = " + command.getEquivalentShuntId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the EquivalentShunt via EquivalentShuntFetchOneSummary
   *
   * @param summary EquivalentShuntFetchOneSummary
   * @return EquivalentShuntFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public EquivalentShunt getEquivalentShunt(EquivalentShuntFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("EquivalentShuntFetchOneSummary arg cannot be null");

    EquivalentShunt entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      EquivalentShuntValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a EquivalentShunt
      // --------------------------------------
      CompletableFuture<EquivalentShunt> futureEntity =
          queryGateway.query(
              new FindEquivalentShuntQuery(
                  new LoadEquivalentShuntFilter(summary.getEquivalentShuntId())),
              ResponseTypes.instanceOf(EquivalentShunt.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate EquivalentShunt with id " + summary.getEquivalentShuntId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all EquivalentShunts
   *
   * @return List<EquivalentShunt>
   * @exception ProcessingException Thrown if any problems
   */
  public List<EquivalentShunt> getAllEquivalentShunt() throws ProcessingException {
    List<EquivalentShunt> list = null;

    try {
      CompletableFuture<List<EquivalentShunt>> futureList =
          queryGateway.query(
              new FindAllEquivalentShuntQuery(),
              ResponseTypes.multipleInstancesOf(EquivalentShunt.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all EquivalentShunt";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign B on EquivalentShunt
   *
   * @param command AssignBToEquivalentShuntCommand
   * @exception ProcessingException
   */
  public void assignB(AssignBToEquivalentShuntCommand command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getEquivalentShuntId());

    SusceptanceBusinessDelegate childDelegate =
        SusceptanceBusinessDelegate.getSusceptanceInstance();
    EquivalentShuntBusinessDelegate parentDelegate =
        EquivalentShuntBusinessDelegate.getEquivalentShuntInstance();
    UUID childId = command.getAssignment().getSusceptanceId();
    Susceptance child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      EquivalentShuntValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get Susceptance using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign B on EquivalentShunt
   *
   * @param command UnAssignBFromEquivalentShuntCommand
   * @exception ProcessingException
   */
  public void unAssignB(UnAssignBFromEquivalentShuntCommand command) throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      EquivalentShuntValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign B on EquivalentShunt";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * assign G on EquivalentShunt
   *
   * @param command AssignGToEquivalentShuntCommand
   * @exception ProcessingException
   */
  public void assignG(AssignGToEquivalentShuntCommand command) throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getEquivalentShuntId());

    ConductanceBusinessDelegate childDelegate =
        ConductanceBusinessDelegate.getConductanceInstance();
    EquivalentShuntBusinessDelegate parentDelegate =
        EquivalentShuntBusinessDelegate.getEquivalentShuntInstance();
    UUID childId = command.getAssignment().getConductanceId();
    Conductance child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      EquivalentShuntValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get Conductance using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign G on EquivalentShunt
   *
   * @param command UnAssignGFromEquivalentShuntCommand
   * @exception ProcessingException
   */
  public void unAssignG(UnAssignGFromEquivalentShuntCommand command) throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      EquivalentShuntValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign G on EquivalentShunt";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return EquivalentShunt
   */
  private EquivalentShunt load(UUID id) throws ProcessingException {
    equivalentShunt =
        EquivalentShuntBusinessDelegate.getEquivalentShuntInstance()
            .getEquivalentShunt(new EquivalentShuntFetchOneSummary(id));
    return equivalentShunt;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private EquivalentShunt equivalentShunt = null;
  private static final Logger LOGGER =
      Logger.getLogger(EquivalentShuntBusinessDelegate.class.getName());
}
