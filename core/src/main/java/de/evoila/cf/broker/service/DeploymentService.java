package de.evoila.cf.broker.service;

import java.util.Map;

import de.evoila.cf.broker.exception.AsyncRequiredException;
import de.evoila.cf.broker.exception.ServerviceInstanceBindingDoesNotExistsException;
import de.evoila.cf.broker.exception.ServiceBrokerException;
import de.evoila.cf.broker.exception.ServiceDefinitionDoesNotExistException;
import de.evoila.cf.broker.exception.ServiceInstanceBindingExistsException;
import de.evoila.cf.broker.exception.ServiceInstanceDoesNotExistException;
import de.evoila.cf.broker.exception.ServiceInstanceExistsException;
import de.evoila.cf.broker.model.CreateServiceInstanceResponse;
import de.evoila.cf.broker.model.JobProgress;
import de.evoila.cf.broker.model.ServiceInstanceBindingResponse;

/**
 * Handles instances of service definitions.
 * 
 * @author sgreenberg@gopivotal.com
 */
public interface DeploymentService {

	// /**
	// * @return All known ServiceInstances
	// */
	// List<ServiceInstance> getAllServiceInstances();

	/**
	 * Create a new instance of a service
	 * 
	 * @param service
	 *            The service definition of the instance to create
	 * @param serviceInstanceId
	 *            The id of the instance provided by the CloudController
	 * @param planId
	 *            The id of the plan for this instance
	 * @param organizationGuid
	 *            The guid of the org this instance belongs to
	 * @param spaceGuid
	 *            The guid of the space this instance belongs to
	 * @return The newly created ServiceInstance
	 * @throws ServiceInstanceExistsException
	 *             if the service instance already exists.
	 * @throws ServiceBrokerException
	 *             if something goes wrong internally
	 */
	CreateServiceInstanceResponse createServiceInstance(String serviceInstanceId, String serviceDefinitionId,
			String planId, String organizationGuid, String spaceGuid, Map<String, String> parameters)
					throws ServiceInstanceExistsException, ServiceBrokerException, AsyncRequiredException,
					ServiceDefinitionDoesNotExistException;

	/**
	 * @param id
	 * @return The ServiceInstance with the given id or null if one does not
	 *         exist
	 */
	// ServiceInstance getServiceInstance(String id);

	/**
	 * Delete and return the instance if it exists.
	 * 
	 * @param id
	 * @return The delete ServiceInstance or null if one did not exist.
	 * @throws ServiceBrokerException
	 *             is something goes wrong internally
	 */
	void deleteServiceInstance(String id)
			throws ServiceBrokerException, ServiceInstanceDoesNotExistException, AsyncRequiredException;

	/**
	 * Create a new binding to a service instance.
	 * 
	 * @param bindingId
	 *            The id provided by the cloud controller
	 * @param instanceId
	 *            The id of the service instance
	 * @param serviceId
	 *            The id of the service
	 * @param planId
	 *            The plan used for this binding
	 * @param appGuid
	 *            The guid of the app for the binding
	 * @return
	 * @throws ServiceInstanceBindingExistsException
	 *             if the same binding already exists.
	 * @throws ServiceBrokerException
	 * @throws ServiceInstanceDoesNotExistException
	 */
	public ServiceInstanceBindingResponse createServiceInstanceBinding(String bindingId, String instanceId,
			String serviceId, String planId, String appGuid)
					throws ServiceInstanceBindingExistsException, ServiceBrokerException,
					ServiceInstanceDoesNotExistException, ServiceDefinitionDoesNotExistException;

	/**
	 * @param id
	 * @return The ServiceInstanceBinding or null if one does not exist.
	 */
	// ServiceInstanceBinding getServiceInstanceBinding(String id);

	/**
	 * Delete the service instance binding. If a binding doesn't exist, return
	 * null.
	 * 
	 * @param id
	 * @throws ServiceBrokerException
	 * @throws ServerviceInstanceBindingDoesNotExistsException
	 */
	void deleteServiceInstanceBinding(String id)
			throws ServiceBrokerException, ServerviceInstanceBindingDoesNotExistsException;

	JobProgress getLastOperation(String serviceInstanceId)
			throws ServiceInstanceDoesNotExistException, ServiceBrokerException;

}