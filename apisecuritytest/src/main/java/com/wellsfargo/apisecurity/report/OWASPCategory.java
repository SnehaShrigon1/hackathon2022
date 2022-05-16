package com.wellsfargo.apisecurity.report;

public enum OWASPCategory {
	API1_2019("API1:2019 Broken Object Level Authorization", "SEV1","Unauthorized access can result in data disclosure to unauthorized parties, data loss, or data manipulation. Unauthorized access to objects can also lead to full account takeover.", "Implement a proper authorization mechanism that relies on the user policies and hierarchy.\r\n"
			+ "Use an authorization mechanism to check if the logged-in user has access to perform the requested action on the record in every function that uses an input from the client to access a record in the database.\r\n"
			+ "Prefer to use random and unpredictable values as GUIDs for records’ IDs.\r\n"
			+ "Write tests to evaluate the authorization mechanism. Do not deploy vulnerable changes that break the tests."), 
	
	API2_2019("Broken User Authentication", "SEV2","Attackers can gain control to other users’ accounts in the system, read their personal data, and perform sensitive actions on their behalf, like money transactions and sending personal messages.", "Make sure you know all the possible flows to authenticate to the API (mobile/ web/deep links that implement one-click authentication/etc.)\r\n"
			+ "Ask your engineers what flows you missed.\r\n"
			+ "Read about your authentication mechanisms. Make sure you understand what and how they are used. OAuth is not authentication, and neither is API keys.\r\n"
			+ "Don't reinvent the wheel in authentication, token generation, password storage. Use the standards.\r\n"
			+ "Credential recovery/forget password endpoints should be treated as login endpoints in terms of brute force, rate limiting, and lockout protections.\r\n"
			+ "Use the OWASP Authentication Cheatsheet."),
	
	
	API3_2019("Excessive Data Exposure", "SEV3","Excessive Data Exposure commonly leads to exposure of sensitive data.", "Never rely on the client side to filter sensitive data.\r\n"
			+ "Review the responses from the API to make sure they contain only legitimate data.\r\n"
			+ "Backend engineers should always ask themselves \"who is the consumer of the data?\" before exposing a new API endpoint.\r\n"
			+ "Avoid using generic methods such as to_json() and to_string(). Instead, cherry-pick specific properties you really want to return\r\n"
			+ "Classify sensitive and personally identifiable information (PII) that your application stores and works with, reviewing all API calls returning such information to see if these responses pose a security issue.\r\n"
			+ "Implement a schema-based response validation mechanism as an extra layer of security. As part of this mechanism define and enforce data returned by all API methods, including errors."), 
	
	API4_2019("Lack of Resources & Rate Limiting", "SEV4","Exploitation may lead to DoS, making the API unresponsive or even unavailable.", "Implement a limit on how often a client can call the API within a defined timeframe.\r\n"
			+ "Notify the client when the limit is exceeded by providing the limit number and the time at which the limit will be reset.\r\n"
			+ "Add proper server-side validation for query string and request body parameters, specifically the one that controls the number of records to be returned in the response.\r\n"
			+ "Define and enforce maximum size of data on all incoming payload"),
	
	API5_2019("Broken Function Level Authorization", "SEV5","Allows attackers to access unauthorized functionality. Administrative functions are key targets for this type of attack.", "The enforcement mechanism(s) should deny all access by default, requiring explicit grants to specific roles for access to every function.\r\n"
			+ "Review your API endpoints against function level authorization flaws, while keeping in mind the business logic of the application and groups hierarchy.\r\n"
			+ "Make sure that all of your administrative controllers inherit from an administrative abstract controller that implements authorization checks based on the user’s group/role.\r\n"
			+ "Make sure that administrative functions inside a regular controller implements authorization checks based on the user’s group and role."), 
	
	API6_2019("Mass Assignment", "SEV6","Exploitation may lead to privilege escalation, data tampering, bypass of security mechanisms, and more", "Avoid using functions that automatically bind a client’s input into code variables or internal objects.\r\n"
			+ "Whitelist only the properties that should be updated by the client.\r\n"
			+ "Use built-in features to blacklist properties that should not be accessed by clients.\r\n"
			+ "If applicable, explicitly define and enforce schemas for the input data payloads."),
	
	API7_2019("Security Misconfiguration", "SEV7","Security misconfigurations can not only expose sensitive user data, but also system details that may lead to full server compromise.", "A repeatable hardening process leading to fast and easy deployment of a properly locked down environment.\r\n"
			+ "A task to review and update configurations across the entire API stack. The review should include: orchestration files, API components, and cloud services (e.g., S3 bucket permissions).\r\n"
			+ "A secure communication channel for all API interactions access to static assets (e.g., images).\r\n"
			+ "An automated process to continuously assess the effectiveness of the configuration and settings in all environments."),
	
	API8_2019("Injection", "SEV8", "API is vulnerable to SQL injection vulnerability which allows login bypass to access private data",
			"The preferred option is to use a safe API, which avoids using the interpreter entirely, provides a parameterized interface, or migrates to Object Relational Mapping Tools (ORMs).\r\n"
			+ "Note: Even when parameterized, stored procedures can still introduce SQL injection if PL/SQL or T-SQL concatenates queries and data or executes hostile data with EXECUTE IMMEDIATE or exec(). Use positive server-side input validation. This is not a complete defense as many applications require special characters, such as text areas or APIs for mobile applications."),
	
	API9_2019("Improper Assets Management", "SEV9", "Attackers may gain access to sensitive data, or even takeover the server through old, unpatched API versions connected to the same database.", "Inventory all API hosts and document important aspects of each one of them, focusing on the API environment (e.g., production, staging, test, development), who should have network access to the host (e.g., public, internal, partners) and the API version.\r\n"
			+ "Inventory integrated services and document important aspects such as their role in the system, what data is exchanged (data flow), and its sensitivity."),
	
	API10_2019("Insufficient Logging & Monitoring", "SEV10","Without visibility over on-going malicious activities, attackers have plenty of time to fully compromise systems.", "Log all failed authentication attempts, denied access, and input validation errors.\r\n"
			+ "Logs should be written using a format suited to be consumed by a log management solution, and should include enough detail to identify the malicious actor.\r\n"
			+ "Logs should be handled as sensitive data, and their integrity should be guaranteed at rest and transit.");

	public final String label;
	public final String severity;
	public final String description;
	public final String remediation;

	private OWASPCategory(String label, String severity, String description, String remediation) {
		this.label = label;
		this.severity = severity;
		this.description = description;
		this.remediation = remediation;
	}

}
