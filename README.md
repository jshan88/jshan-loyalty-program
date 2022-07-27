# jshan-loyalty-program
simple loyalty program - a small, side project that i'm wokring on off-work.

# Current Process ; 25%
■■■■■■■■■■■■■■■##################################################
1. Done with the basic framework set-up. 
2. APIs implemented so far. 
 
    ~ member : enrollment (post) / update (put) / get (retrieval).
    
    ~ accrual(적립) : accrual request(post) / accrual cancel(put) apis.
    
    ~ redemption(소진) : postCart / getCart / orderRequest / orderRefund

# DB Table Schema for the program. 
- The program will be implemented based on the below schema. (refer to the below links)
1) Member-related : https://dbdiagram.io/d/622ac3b761d06e6eade19ba2
2) Transaction-related : https://dbdiagram.io/d/622af32d61d06e6eade2e09b
- Minor changes might take place as the implementation goes..  
  
# Application is being implemented with... 
1. Java / Springboot 
2. Spring data JPA
3. Deployed in private AWS env. (ECS Fargate, Postgresql)
