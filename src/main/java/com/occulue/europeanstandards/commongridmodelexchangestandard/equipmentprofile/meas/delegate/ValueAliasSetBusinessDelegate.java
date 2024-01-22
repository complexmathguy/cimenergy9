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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas.delegate;

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
 * ValueAliasSet business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of ValueAliasSet related services in the case of a ValueAliasSet
 *       business related service failing.
 *   <li>Exposes a simpler, uniform ValueAliasSet interface to the business tier, making it easy for
 *       clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill ValueAliasSet business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class ValueAliasSetBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public ValueAliasSetBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * ValueAliasSet Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return ValueAliasSetBusinessDelegate
   */
  public static ValueAliasSetBusinessDelegate getValueAliasSetInstance() {
    return (new ValueAliasSetBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createValueAliasSet(CreateValueAliasSetCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getValueAliasSetId() == null) command.setValueAliasSetId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      ValueAliasSetValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateValueAliasSetCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateValueAliasSetCommand of ValueAliasSet is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create ValueAliasSet - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateValueAliasSetCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateValueAliasSet(UpdateValueAliasSetCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      ValueAliasSetValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateValueAliasSetCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save ValueAliasSet - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteValueAliasSetCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteValueAliasSetCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ValueAliasSetValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteValueAliasSetCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete ValueAliasSet using Id = " + command.getValueAliasSetId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the ValueAliasSet via ValueAliasSetFetchOneSummary
   *
   * @param summary ValueAliasSetFetchOneSummary
   * @return ValueAliasSetFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public ValueAliasSet getValueAliasSet(ValueAliasSetFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("ValueAliasSetFetchOneSummary arg cannot be null");

    ValueAliasSet entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      ValueAliasSetValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a ValueAliasSet
      // --------------------------------------
      CompletableFuture<ValueAliasSet> futureEntity =
          queryGateway.query(
              new FindValueAliasSetQuery(new LoadValueAliasSetFilter(summary.getValueAliasSetId())),
              ResponseTypes.instanceOf(ValueAliasSet.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate ValueAliasSet with id " + summary.getValueAliasSetId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all ValueAliasSets
   *
   * @return List<ValueAliasSet>
   * @exception ProcessingException Thrown if any problems
   */
  public List<ValueAliasSet> getAllValueAliasSet() throws ProcessingException {
    List<ValueAliasSet> list = null;

    try {
      CompletableFuture<List<ValueAliasSet>> futureList =
          queryGateway.query(
              new FindAllValueAliasSetQuery(),
              ResponseTypes.multipleInstancesOf(ValueAliasSet.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all ValueAliasSet";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return ValueAliasSet
   */
  private ValueAliasSet load(UUID id) throws ProcessingException {
    valueAliasSet =
        ValueAliasSetBusinessDelegate.getValueAliasSetInstance()
            .getValueAliasSet(new ValueAliasSetFetchOneSummary(id));
    return valueAliasSet;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private ValueAliasSet valueAliasSet = null;
  private static final Logger LOGGER =
      Logger.getLogger(ValueAliasSetBusinessDelegate.class.getName());
}
