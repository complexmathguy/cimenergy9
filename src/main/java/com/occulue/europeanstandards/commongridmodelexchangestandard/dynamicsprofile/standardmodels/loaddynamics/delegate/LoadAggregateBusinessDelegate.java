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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.loaddynamics.delegate;

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
 * LoadAggregate business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of LoadAggregate related services in the case of a LoadAggregate
 *       business related service failing.
 *   <li>Exposes a simpler, uniform LoadAggregate interface to the business tier, making it easy for
 *       clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill LoadAggregate business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class LoadAggregateBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public LoadAggregateBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * LoadAggregate Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return LoadAggregateBusinessDelegate
   */
  public static LoadAggregateBusinessDelegate getLoadAggregateInstance() {
    return (new LoadAggregateBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createLoadAggregate(CreateLoadAggregateCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getLoadAggregateId() == null) command.setLoadAggregateId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      LoadAggregateValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateLoadAggregateCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateLoadAggregateCommand of LoadAggregate is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create LoadAggregate - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateLoadAggregateCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateLoadAggregate(UpdateLoadAggregateCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      LoadAggregateValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateLoadAggregateCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save LoadAggregate - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteLoadAggregateCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteLoadAggregateCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      LoadAggregateValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteLoadAggregateCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete LoadAggregate using Id = " + command.getLoadAggregateId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the LoadAggregate via LoadAggregateFetchOneSummary
   *
   * @param summary LoadAggregateFetchOneSummary
   * @return LoadAggregateFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public LoadAggregate getLoadAggregate(LoadAggregateFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("LoadAggregateFetchOneSummary arg cannot be null");

    LoadAggregate entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      LoadAggregateValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a LoadAggregate
      // --------------------------------------
      CompletableFuture<LoadAggregate> futureEntity =
          queryGateway.query(
              new FindLoadAggregateQuery(new LoadLoadAggregateFilter(summary.getLoadAggregateId())),
              ResponseTypes.instanceOf(LoadAggregate.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate LoadAggregate with id " + summary.getLoadAggregateId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all LoadAggregates
   *
   * @return List<LoadAggregate>
   * @exception ProcessingException Thrown if any problems
   */
  public List<LoadAggregate> getAllLoadAggregate() throws ProcessingException {
    List<LoadAggregate> list = null;

    try {
      CompletableFuture<List<LoadAggregate>> futureList =
          queryGateway.query(
              new FindAllLoadAggregateQuery(),
              ResponseTypes.multipleInstancesOf(LoadAggregate.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all LoadAggregate";
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
   * @return LoadAggregate
   */
  private LoadAggregate load(UUID id) throws ProcessingException {
    loadAggregate =
        LoadAggregateBusinessDelegate.getLoadAggregateInstance()
            .getLoadAggregate(new LoadAggregateFetchOneSummary(id));
    return loadAggregate;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private LoadAggregate loadAggregate = null;
  private static final Logger LOGGER =
      Logger.getLogger(LoadAggregateBusinessDelegate.class.getName());
}
