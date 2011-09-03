MakeATest-RMI Plugin
====================
**Vis�o Geral do Plugin**

Este plugin tem como objetivo permitir a cria��o de testes de unidade para projetos de sistemas distribu�dos em Java utilizando RMI de maneira que a configura��o de inicializa��o, simula��o e prepara��o do ambiente de testes com os objetos remotos possam ser realizadas atrav�s de suas anota��es.

Utiliza��o
----------

*Exemplo*:


### Classe de Teste de Unidade (*JUnit*)

@RunWith(MakeATestRunner.class)
@StartRMIServer(codebasePath="C:\\Java\\Workspace\\ChatMockFrameworkRMITest\\chat-common\\",
				securityPolicyPath="C:\\Java\\Workspace\\ChatMockFrameworkRMITest\\chat-test\\")
@StopRMIServer()				
public class ChatRMITestServerMock {
	
	public Mockery context;	
	public IMessage server;
	
	// IMessage
	public static IMessage chatServer;
		
    @Test
    @PutMockRMI(fixtureName="server", serverName="itachat", serverPort=1099)
    @RemoveMockRMI(serverName="itachat")
	public void testServerJoinMessage() throws RemoteException, MalformedURLException, NotBoundException {
		// Lookup Object
		// -->	   
    	System.out.println("testServerJoinMessage");
		System.setSecurityManager (new RMISecurityManager());
		Remote remoteObject = Naming.lookup("itachat");
		chatServer = (IMessage)remoteObject ;
    	
    	// INotify
    	final INotify stubNotify = null;
    	
    	// Expectation
        context.checking(new Expectations() {{
        	one(server).join(with(any(INotify.class)), with(any(String.class)));
        	will(returnValue(true));
        }});
               
        // Assert
        assertTrue(chatServer.join(stubNotify, "Felipe"));
        context.assertIsSatisfied();
	}
    
    @Test
    @PutMockRMI(fixtureName="server", serverName="itachat-2", serverPort=1099)
    @RemoveMockRMI(serverName="itachat-2")
	public void testServerJoinMessageOtherServer() throws RemoteException, MalformedURLException, NotBoundException {
		// Lookup Object
		// -->
    	System.out.println("testServerJoinMessageOtherServer");
		System.setSecurityManager (new RMISecurityManager());
		Remote remoteObject = Naming.lookup("itachat-2");
		chatServer = (IMessage)remoteObject ;
    	
    	// INotify
    	final INotify stubNotify = null;
    	
    	// Expectation
        context.checking(new Expectations() {{
        	one(server).join(with(any(INotify.class)), with(any(String.class)));
        	will(returnValue(true));
        }});
               
        // Assert
        assertTrue(chatServer.join(stubNotify, "Felipe"));
        context.assertIsSatisfied();
	}
    
}

```

Anota��es
---------

As anota��es do plugin para testes de unidade com sistemas distribu�dos em Java com RMI.

*   Date object
*   Milliseconds

*   @StartRMIServer
*Inicializa o servi�o RMI com as configura��es iniciais*
*   @StopRMIServer
*Para o servi�o RMI*
*   @PutMockRMI
*Inclui um objeto remoto (Mock Object) no servi�o RMI*
*   @RemoveMockRMI
*Remove do servi�o RMI o objeto remoto (Mock Object)*
*   @AssertObjectOnServer
*Verifica se o objeto remoto est� no RMI*
*   @AssertObjectNotOnServer
*Verifica se o objeto remoto n�o est� no RMI*


Depend�ncias
------------

*   Eclipe
*   JUnit 4
*   JMock
*   MakeATest-Core


Licen�a
-------

Copyright (c) 20011 Felipe Olivers. This is a free software is licensed under the MIT License.