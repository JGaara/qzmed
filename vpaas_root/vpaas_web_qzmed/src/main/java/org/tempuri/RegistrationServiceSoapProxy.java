package org.tempuri;

public class RegistrationServiceSoapProxy implements org.tempuri.RegistrationServiceSoap {
  private String _endpoint = null;
  private org.tempuri.RegistrationServiceSoap registrationServiceSoap = null;
  
  public RegistrationServiceSoapProxy() {
    _initRegistrationServiceSoapProxy();
  }
  
  public RegistrationServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegistrationServiceSoapProxy();
  }
  
  private void _initRegistrationServiceSoapProxy() {
    try {
      registrationServiceSoap = (new org.tempuri.RegistrationServiceLocator()).getRegistrationServiceSoap();
      if (registrationServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)registrationServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)registrationServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (registrationServiceSoap != null)
      ((javax.xml.rpc.Stub)registrationServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.RegistrationServiceSoap getRegistrationServiceSoap() {
    if (registrationServiceSoap == null)
      _initRegistrationServiceSoapProxy();
    return registrationServiceSoap;
  }
  
  public java.lang.String do_Transaction(java.lang.String inputData) throws java.rmi.RemoteException{
    if (registrationServiceSoap == null)
      _initRegistrationServiceSoapProxy();
    return registrationServiceSoap.do_Transaction(inputData);
  }
  
  
}