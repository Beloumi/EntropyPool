# EntropyPool
Entropy pool provides seeds for cryptographic secure random number generators

Entropy pool generated from key strokes and mouse events of graphical user interfaces 
and the Java thread schedule. The pool provides additional seed values for settings 
where the support of a proper random number generators by the operating system is not available. 

How it works:
64 threads of the same priority update a table of 512 byte (the entropy pool) by performing 
various Xorshift generators. These threads are updated with the following values:
- time between key strokes
- time between mouse events and mouse positions
- systems nano time
But the most of the randomness comes from the Java thread schedule. 

For which scenario:
Unix-like systems collect entropy and provides random values via /dev/random and /dev/urandom. 
The quality and reliability of these random data are quite good and there is no need for additional seeds. 
But on other operating systems the supported random generators are less trustworthy. For closed source 
operating systems, no one can check how the access to these files is managed, if there is a weakness, 
a bug or a backdoor inside the generator. For those circumstances the SeedPool provides seed values to 
reseed random number generators. 

Example:

// Register the Collectors for graphical user interfaces:
textField.addKeyListener(new KeyRandomCollector() );
panel.addMouseMotionListener(new MouseRandomCollector() );

// get the EntropyPool instance
EntropyPool entropyPool = EntropyPool.getInstance();

// This will choose the random generator 
// supported of the operating system if available: 
SecureRandom random = new SecureRandom();

// This will reseed the existing seed, so there is 
//a guaranty that the randomness is never reduced: 
random.setSeed( entropyPool.getEntropy(true) ); // stop the collection

// use the random generator: 
byte randomBytes[] = new byte[16];
random.nextBytes(randomBytes);
…
// reseed the pool after 64 values
// if the collection was stopped
