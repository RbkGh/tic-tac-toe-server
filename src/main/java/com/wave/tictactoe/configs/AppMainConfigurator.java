package com.wave.tictactoe.configs;

import com.wave.tictactoe.base.OptimalMoveGenerator;
import com.wave.tictactoe.base.OptimalMoveGeneratorTicTacImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 4:18 AM
 */
@Configuration
public class AppMainConfigurator {

    @Bean
    public OptimalMoveGenerator optimalMoveGenerator() {
        //here we have configured the tic-tac-toe implementation class as the default implementaiton to the default interface
        return new OptimalMoveGeneratorTicTacImplementation();
    }
    
}
