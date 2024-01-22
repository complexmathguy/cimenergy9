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
 * StringMeasurement business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of StringMeasurement related services in the case of a
 *       StringMeasurement business related service failing.
 *   <li>Exposes a simpler, uniform StringMeasurement interface to the business tier, making it easy
 *       for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill StringMeasurement business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class StringMeasurementBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public StringMeasurementBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * StringMeasurement Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return StringMeasurementBusinessDelegate
   */
  public static StringMeasurementBusinessDelegate getStringMeasurementInstance() {
    return (new StringMeasurementBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createStringMeasurement(CreateStringMeasurementCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getStringMeasurementId() == null)
        command.setStringMeasurementId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      StringMeasurementValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateStringMeasurementCommand - by convention the future return value for a
      // create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateStringMeasurementCommand of StringMeasurement is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create StringMeasurement - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateStringMeasurementCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateStringMeasurement(UpdateStringMeasurementCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      StringMeasurementValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateStringMeasurementCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save StringMeasurement - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteStringMeasurementCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteStringMeasurementCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      StringMeasurementValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteStringMeasurementCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete StringMeasurement using Id = " + command.getStringMeasurementId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the StringMeasurement via StringMeasurementFetchOneSummary
   *
   * @param summary StringMeasurementFetchOneSummary
   * @return StringMeasurementFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public StringMeasurement getStringMeasurement(StringMeasurementFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("StringMeasurementFetchOneSummary arg cannot be null");

    StringMeasurement entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      StringMeasurementValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a StringMeasurement
      // --------------------------------------
      CompletableFuture<StringMeasurement> futureEntity =
          queryGateway.query(
              new FindStringMeasurementQuery(
                  new LoadStringMeasurementFilter(summary.getStringMeasurementId())),
              ResponseTypes.instanceOf(StringMeasurement.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate StringMeasurement with id " + summary.getStringMeasurementId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all StringMeasurements
   *
   * @return List<StringMeasurement>
   * @exception ProcessingException Thrown if any problems
   */
  public List<StringMeasurement> getAllStringMeasurement() throws ProcessingException {
    List<StringMeasurement> list = null;

    try {
      CompletableFuture<List<StringMeasurement>> futureList =
          queryGateway.query(
              new FindAllStringMeasurementQuery(),
              ResponseTypes.multipleInstancesOf(StringMeasurement.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all StringMeasurement";
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
   * @return StringMeasurement
   */
  private StringMeasurement load(UUID id) throws ProcessingException {
    stringMeasurement =
        StringMeasurementBusinessDelegate.getStringMeasurementInstance()
            .getStringMeasurement(new StringMeasurementFetchOneSummary(id));
    return stringMeasurement;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private StringMeasurement stringMeasurement = null;
  private static final Logger LOGGER =
      Logger.getLogger(StringMeasurementBusinessDelegate.class.getName());
}
