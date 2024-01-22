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
 * IntegerProxy business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of IntegerProxy related services in the case of a IntegerProxy
 *       business related service failing.
 *   <li>Exposes a simpler, uniform IntegerProxy interface to the business tier, making it easy for
 *       clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill IntegerProxy business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class IntegerProxyBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public IntegerProxyBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * IntegerProxy Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return IntegerProxyBusinessDelegate
   */
  public static IntegerProxyBusinessDelegate getIntegerProxyInstance() {
    return (new IntegerProxyBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createIntegerProxy(CreateIntegerProxyCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getIntegerProxyId() == null) command.setIntegerProxyId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      IntegerProxyValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateIntegerProxyCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateIntegerProxyCommand of IntegerProxy is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create IntegerProxy - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateIntegerProxyCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateIntegerProxy(UpdateIntegerProxyCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      IntegerProxyValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateIntegerProxyCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save IntegerProxy - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteIntegerProxyCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteIntegerProxyCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      IntegerProxyValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteIntegerProxyCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete IntegerProxy using Id = " + command.getIntegerProxyId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the IntegerProxy via IntegerProxyFetchOneSummary
   *
   * @param summary IntegerProxyFetchOneSummary
   * @return IntegerProxyFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public IntegerProxy getIntegerProxy(IntegerProxyFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("IntegerProxyFetchOneSummary arg cannot be null");

    IntegerProxy entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      IntegerProxyValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a IntegerProxy
      // --------------------------------------
      CompletableFuture<IntegerProxy> futureEntity =
          queryGateway.query(
              new FindIntegerProxyQuery(new LoadIntegerProxyFilter(summary.getIntegerProxyId())),
              ResponseTypes.instanceOf(IntegerProxy.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg = "Unable to locate IntegerProxy with id " + summary.getIntegerProxyId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all IntegerProxys
   *
   * @return List<IntegerProxy>
   * @exception ProcessingException Thrown if any problems
   */
  public List<IntegerProxy> getAllIntegerProxy() throws ProcessingException {
    List<IntegerProxy> list = null;

    try {
      CompletableFuture<List<IntegerProxy>> futureList =
          queryGateway.query(
              new FindAllIntegerProxyQuery(),
              ResponseTypes.multipleInstancesOf(IntegerProxy.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all IntegerProxy";
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
   * @return IntegerProxy
   */
  private IntegerProxy load(UUID id) throws ProcessingException {
    integerProxy =
        IntegerProxyBusinessDelegate.getIntegerProxyInstance()
            .getIntegerProxy(new IntegerProxyFetchOneSummary(id));
    return integerProxy;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private IntegerProxy integerProxy = null;
  private static final Logger LOGGER =
      Logger.getLogger(IntegerProxyBusinessDelegate.class.getName());
}
