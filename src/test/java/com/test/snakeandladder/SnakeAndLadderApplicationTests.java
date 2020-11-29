package com.test.snakeandladder;
import static io.restassured.RestAssured.expect;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.snakeandladder.constants.DiceType;
import com.test.snakeandladder.dto.SnakeAndLadderDto;
import com.test.snakeandladder.model.Ladder;
import com.test.snakeandladder.model.Player;
import com.test.snakeandladder.model.Snake;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SnakeAndLadderApplication.class ,webEnvironment = WebEnvironment.DEFINED_PORT)
public class SnakeAndLadderApplicationTests {

	@Test
	public void contextLoads() {

		List<Snake> snakes = new ArrayList<Snake>();
		snakes.add(new Snake(14, 7));
		snakes.add(new Snake(25, 10));
		snakes.add(new Snake(95, 28));

		List<Ladder> ladders = new ArrayList<Ladder>();
		ladders.add(new Ladder(0, 0));

		List<Player> players = new ArrayList<Player>();
		players.add(new Player("Jet"));
		players.add(new Player("MS"));
		players.add(new Player("FOO"));

		SnakeAndLadderDto dto = new SnakeAndLadderDto();
		dto.setLadders(ladders);
		dto.setPlayers(players);
		dto.setSnakes(snakes);
		dto.setDiceType(DiceType.NORMAL);
		
		expect()
		  .statusCode(200)
		  .response().		 
		given()
		  .header("secretKey", "secret")
		  .body(dto)
		  .contentType(MediaType.APPLICATION_JSON_VALUE).
		 when()
		  .get("/snakeAndLadder/startGame");
	}
}