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
package com.occulue.europeanstandards.commongridmodelexchangestandard.diagramlayoutprofile.diagramlayout.delegate;

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
 * DiagramObjectStyle business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of DiagramObjectStyle related services in the case of a
 *       DiagramObjectStyle business related service failing.
 *   <li>Exposes a simpler, uniform DiagramObjectStyle interface to the business tier, making it
 *       easy for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill DiagramObjectStyle
 *       business related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class DiagramObjectStyleBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public DiagramObjectStyleBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * DiagramObjectStyle Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return DiagramObjectStyleBusinessDelegate
   */
  public static DiagramObjectStyleBusinessDelegate getDiagramObjectStyleInstance() {
    return (new DiagramObjectStyleBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createDiagramObjectStyle(CreateDiagramObjectStyleCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getDiagramObjectStyleId() == null)
        command.setDiagramObjectStyleId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      DiagramObjectStyleValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateDiagramObjectStyleCommand - by convention the future return value for a
      // create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateDiagramObjectStyleCommand of DiagramObjectStyle is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create DiagramObjectStyle - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateDiagramObjectStyleCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateDiagramObjectStyle(UpdateDiagramObjectStyleCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      DiagramObjectStyleValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateDiagramObjectStyleCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save DiagramObjectStyle - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteDiagramObjectStyleCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteDiagramObjectStyleCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      DiagramObjectStyleValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteDiagramObjectStyleCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete DiagramObjectStyle using Id = " + command.getDiagramObjectStyleId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the DiagramObjectStyle via DiagramObjectStyleFetchOneSummary
   *
   * @param summary DiagramObjectStyleFetchOneSummary
   * @return DiagramObjectStyleFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public DiagramObjectStyle getDiagramObjectStyle(DiagramObjectStyleFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("DiagramObjectStyleFetchOneSummary arg cannot be null");

    DiagramObjectStyle entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      DiagramObjectStyleValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a DiagramObjectStyle
      // --------------------------------------
      CompletableFuture<DiagramObjectStyle> futureEntity =
          queryGateway.query(
              new FindDiagramObjectStyleQuery(
                  new LoadDiagramObjectStyleFilter(summary.getDiagramObjectStyleId())),
              ResponseTypes.instanceOf(DiagramObjectStyle.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate DiagramObjectStyle with id " + summary.getDiagramObjectStyleId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all DiagramObjectStyles
   *
   * @return List<DiagramObjectStyle>
   * @exception ProcessingException Thrown if any problems
   */
  public List<DiagramObjectStyle> getAllDiagramObjectStyle() throws ProcessingException {
    List<DiagramObjectStyle> list = null;

    try {
      CompletableFuture<List<DiagramObjectStyle>> futureList =
          queryGateway.query(
              new FindAllDiagramObjectStyleQuery(),
              ResponseTypes.multipleInstancesOf(DiagramObjectStyle.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all DiagramObjectStyle";
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
   * @return DiagramObjectStyle
   */
  private DiagramObjectStyle load(UUID id) throws ProcessingException {
    diagramObjectStyle =
        DiagramObjectStyleBusinessDelegate.getDiagramObjectStyleInstance()
            .getDiagramObjectStyle(new DiagramObjectStyleFetchOneSummary(id));
    return diagramObjectStyle;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private DiagramObjectStyle diagramObjectStyle = null;
  private static final Logger LOGGER =
      Logger.getLogger(DiagramObjectStyleBusinessDelegate.class.getName());
}
