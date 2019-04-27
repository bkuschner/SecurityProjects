//
//  main.cpp
//  PasswordVerifier
//
//  Created by Benjamin Kuschner on 4/26/19.
//  Copyright © 2019 Benjamin Kuschner. All rights reserved.
//

#include <iostream>
#include <vector>
#include <utility>
#include <string>

/*
 takes as input a password and checks if it is valid according to the following rules:
 at least one lowercase letter, uppercase letter, number, and special character, minimum length 8, and no special characters outside the allowed set. The allowed special characters are the standard ones found on the American keyboard excluding whitespace.
 @param pwd password to be verified
 @return true if pwd is valid, false otherwise
 */
bool isValid (std::string pwd) {
    if (pwd.length() < 8) return false;
    bool hasLower = false;
    bool hasUpper = false;
    bool hasNumber = false;
    bool hasSpecial = false;
    char current;
    for (int i = 0; i < pwd.length(); ++i) {
        current = pwd.at(i);
        if(current < '!' || current > '~') //invalid character
            return false;
        else if(current >= 'a' && current <= 'z') //range of lowercase letters
            hasLower = true;
        else if(current >= 'A' && current <= 'Z') //range of uppercase letters
            hasUpper = true;
        else if(current >= '0' && current <= '9') //range of numbers
            hasNumber = true;
        else //if it's valid, and not a letter or number, it's a special char
            hasSpecial = true;
    }
    
    if(hasLower && hasUpper && hasNumber && hasSpecial) //all conditions met
        return true;
    return false;
}

int main() {
    std::vector<std::pair<std::string,bool>> test_cases = std::vector<std::pair<std::string,bool>>();
    test_cases.push_back(std::pair<std::string,bool>("hello123", false));
    test_cases.push_back(std::pair<std::string,bool>("Utter37", false));
    test_cases.push_back(std::pair<std::string,bool>("cliMB0003", false));
    test_cases.push_back(std::pair<std::string,bool>("CHALK12!@12", false));
    test_cases.push_back(std::pair<std::string,bool>("!CRime#226", true));
    test_cases.push_back(std::pair<std::string,bool>("!1Aaaaaa", true));
    test_cases.push_back(std::pair<std::string,bool>("!!!!!Aa1", true));
    test_cases.push_back(std::pair<std::string,bool>("aa55555A@", true));
    test_cases.push_back(std::pair<std::string,bool>("password", false));
    test_cases.push_back(std::pair<std::string,bool>("test", false));
    test_cases.push_back(std::pair<std::string,bool>("1!Aa", false));
    test_cases.push_back(std::pair<std::string,bool>("11@@AAa", false));
    test_cases.push_back(std::pair<std::string,bool>("snarl49001", false));
    
    std::vector<bool> test_results = std::vector<bool>();
    for(int i = 0; i < test_cases.size(); ++i) {
        test_results.push_back(isValid(test_cases.at(i).first));
    }
    std::vector<int> error_indicies = std::vector<int>();
    for(int i = 0; i < test_results.size(); ++i) {
        if(test_results.at(i) != test_cases.at(i).second)
            error_indicies.push_back(i);
    }
    if(error_indicies.size() == 0)
        std::cout << "no errors. Good job!" << std::endl;
    else {
        std::cout << "errors at indicies:" << std::endl;
        for(int i = 0; i < error_indicies.size(); ++i) {
            std::cout << error_indicies.at(i) << std::endl;
        }
    }
    
    return 0;
}
