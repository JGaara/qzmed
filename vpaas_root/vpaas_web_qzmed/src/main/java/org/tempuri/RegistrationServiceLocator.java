/**
 * RegistrationServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class RegistrationServiceLocator extends org.apache.axis.client.Service implements org.tempuri.RegistrationService {

    public RegistrationServiceLocator() {
    }


    public RegistrationServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RegistrationServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RegistrationServiceSoap
    private java.lang.String RegistrationServiceSoap_address = "http://221.1.80.42/URS/SIService/RegistrationService.asmx";

    public java.lang.String getRegistrationServiceSoapAddress() {
        return RegistrationServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RegistrationServiceSoapWSDDServiceName = "RegistrationServiceSoap";

    public java.lang.String getRegistrationServiceSoapWSDDServiceName() {
        return RegistrationServiceSoapWSDDServiceName;
    }

    public void setRegistrationServiceSoapWSDDServiceName(java.lang.String name) {
        RegistrationServiceSoapWSDDServiceName = name;
    }

    public org.tempuri.RegistrationServiceSoap getRegistrationServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RegistrationServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRegistrationServiceSoap(endpoint);
    }

    public org.tempuri.RegistrationServiceSoap getRegistrationServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.RegistrationServiceSoapStub _stub = new org.tempuri.RegistrationServiceSoapStub(portAddress, this);
            _stub.setPortName(getRegistrationServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRegistrationServiceSoapEndpointAddress(java.lang.String address) {
        RegistrationServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.RegistrationServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.RegistrationServiceSoapStub _stub = new org.tempuri.RegistrationServiceSoapStub(new java.net.URL(RegistrationServiceSoap_address), this);
                _stub.setPortName(getRegistrationServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RegistrationServiceSoap".equals(inputPortName)) {
            return getRegistrationServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "RegistrationService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "RegistrationServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RegistrationServiceSoap".equals(portName)) {
            setRegistrationServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
