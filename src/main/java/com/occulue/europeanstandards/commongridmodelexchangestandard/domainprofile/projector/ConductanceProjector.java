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
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Logger;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for Conductance as outlined for the CQRS pattern. All event handling and query handling
 * related to Conductance are invoked here and dispersed as an event to be handled elsewhere.
 *
 * <p>Commands are handled by ConductanceAggregate
 *
 * @author your_name_here
 */
// @ProcessingGroup("conductance")
@Component("conductance-projector")
public class ConductanceProjector extends ConductanceEntityProjector {

  // core constructor
  public ConductanceProjector(
      ConductanceRepository repository, QueryUpdateEmitter queryUpdateEmitter) {
    super(repository);
    this.queryUpdateEmitter = queryUpdateEmitter;
  }

  /*
   * @param	event CreateConductanceEvent
   */
  @EventHandler(payloadType = CreateConductanceEvent.class)
  public void handle(CreateConductanceEvent event) {
    LOGGER.info("handling CreateConductanceEvent - " + event);
    Conductance entity = new Conductance();
    entity.setConductanceId(event.getConductanceId());
    entity.setMultiplier(event.getMultiplier());
    entity.setUnit(event.getUnit());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    create(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductance(entity);
  }

  /*
   * @param	event UpdateConductanceEvent
   */
  @EventHandler(payloadType = UpdateConductanceEvent.class)
  public void handle(UpdateConductanceEvent event) {
    LOGGER.info("handling UpdateConductanceEvent - " + event);

    Conductance entity = new Conductance();
    entity.setConductanceId(event.getConductanceId());
    entity.setMultiplier(event.getMultiplier());
    entity.setUnit(event.getUnit());
    entity.setValue(event.getValue());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    update(entity);

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindConductance(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductance(entity);
  }

  /*
   * @param	event DeleteConductanceEvent
   */
  @EventHandler(payloadType = DeleteConductanceEvent.class)
  public void handle(DeleteConductanceEvent event) {
    LOGGER.info("handling DeleteConductanceEvent - " + event);

    // ------------------------------------------
    // delete delegation
    // ------------------------------------------
    Conductance entity = delete(event.getConductanceId());

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductance(entity);
  }

  /*
   * @param	event AssignValueToConductanceEvent
   */
  @EventHandler(payloadType = AssignValueToConductanceEvent.class)
  public void handle(AssignValueToConductanceEvent event) {
    LOGGER.info("handling AssignValueToConductanceEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    Conductance entity = assignValue(event.getConductanceId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindConductance(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductance(entity);
  }

  /*
   * @param	event UnAssignValueFromConductanceEvent
   */
  @EventHandler(payloadType = UnAssignValueFromConductanceEvent.class)
  public void handle(UnAssignValueFromConductanceEvent event) {
    LOGGER.info("handling UnAssignValueFromConductanceEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    Conductance entity = unAssignValue(event.getConductanceId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindConductance(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllConductance(entity);
  }

  /**
   * Method to retrieve the Conductance via an ConductancePrimaryKey.
   *
   * @param id Long
   * @return Conductance
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public Conductance handle(FindConductanceQuery query)
      throws ProcessingException, IllegalArgumentException {
    return find(query.getFilter().getConductanceId());
  }

  /**
   * Method to retrieve a collection of all Conductances
   *
   * @param query FindAllConductanceQuery
   * @return List<Conductance>
   * @exception ProcessingException Thrown if any problems
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public List<Conductance> handle(FindAllConductanceQuery query) throws ProcessingException {
    return findAll(query);
  }

  /**
   * emit to subscription queries of type FindConductance, but only if the id matches
   *
   * @param entity Conductance
   */
  protected void emitFindConductance(Conductance entity) {
    LOGGER.info("handling emitFindConductance");

    queryUpdateEmitter.emit(
        FindConductanceQuery.class,
        query -> query.getFilter().getConductanceId().equals(entity.getConductanceId()),
        entity);
  }

  /**
   * unconditionally emit to subscription queries of type FindAllConductance
   *
   * @param entity Conductance
   */
  protected void emitFindAllConductance(Conductance entity) {
    LOGGER.info("handling emitFindAllConductance");

    queryUpdateEmitter.emit(FindAllConductanceQuery.class, query -> true, entity);
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired private final QueryUpdateEmitter queryUpdateEmitter;
  private static final Logger LOGGER = Logger.getLogger(ConductanceProjector.class.getName());
}
