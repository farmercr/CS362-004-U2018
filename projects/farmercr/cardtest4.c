/******************************************************************************
* Program Name:	Assignment-3
* Author:			Craig Farmer
* Course:			CS 362 - 400, Summer 2018
* Date Created:	07/16/2018
* Last Modified:	07/22/2018
* Due Date:			07/22/2018
* File name:		cardtest4.c
* Description: A card test for Village card function.
* Websites consulted:
*	https://www.gnu.org/software/make/manual/make.html
******************************************************************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "rngs.h"

#define TESTCARD "Village"
#define RED_TEXT "\x1b[31m"
#define GREEN_TEXT "\x1b[32m"
#define RESET_TEXT "\x1b[0m"

void assertResult(int expected, int actual, char* testDescription)
{
	if (expected == actual)
	{
		printf(GREEN_TEXT "SUCCESS:" RESET_TEXT);
		printf(" %s; Expected: %d, Actual: %d\n", testDescription, expected, actual);
	}
	else
	{
		printf(RED_TEXT "   FAIL:" RESET_TEXT);
		printf(" %s; Expected: %d, Actual: %d\n", testDescription, expected, actual);
	}
}

int main()
{
	//int newCards = 0;
	//int discarded = 1;
	//int xtraCoins = 0;
	//int shuffledCards = 0;

	int i;
	int handpos = 0, choice1 = 0, choice2 = 0, choice3 = 0, bonus = 0;
	//int remove1, remove2;
	int seed = 50;
	int numPlayers = 2;
	int thisPlayer = 0;
	int nextPlayer = 1;
	struct gameState startGame, testGame;
	int k[10] = { adventurer, baron, council_room, cutpurse, mine, minion,
		remodel, smithy, tribute, village };
	const char* kingdomCardNames[] = { "Adventurer", "Baron", "Council Room",
		"Cutpurse", "Mine", "Minion",	"Remodel", "Smithy", "Tribute", "Village" };
	//int startHandTreasure = 0;
	//int testHandTreasure = 0;
	//int currentCard;

	// initialize a game state and player cards
	initializeGame(numPlayers, k, seed, &testGame);

	// copy the gameState to maintain a starting state for a baseline
	memcpy(&startGame, &testGame, sizeof(struct gameState));

	printf("\n     ----- Testing %s Card -----\n", TESTCARD);

	// 'play' the village card
	cardEffect(village, choice1, choice2, choice3, &testGame, handpos, &bonus);

	// test that player played 1 card
	assertResult((startGame.playedCardCount + 1), testGame.playedCardCount, "Player played card count");

	// test player action count increased by 2
	assertResult((startGame.numActions - 1 + 2), (testGame.numActions - 1), "Player action count");

	// test player buy count
	assertResult(startGame.numBuys, testGame.numBuys, "Player buy count");

	// test player hand card count
	assertResult(startGame.handCount[thisPlayer] - 1 + 1), testGame.handCount[thisPlayer], "Player hand count");

	// test that the player deck decreased by 4 cards
	assertResult((startGame.deckCount[thisPlayer] - 1), testGame.deckCount[thisPlayer], "Player deck count");

	// test that player coin count is unchanged
	assertResult(startGame.coins, testGame.coins, "Player coin count");

	// test the treasure card piles
	assertResult(startGame.supplyCount[copper], testGame.supplyCount[copper], "Copper card pile count");
	assertResult(startGame.supplyCount[silver], testGame.supplyCount[silver], "Silver card pile count");
	assertResult(startGame.supplyCount[gold], testGame.supplyCount[gold], "Gold card pile count");

	// test the curse card pile
	assertResult(startGame.supplyCount[curse], testGame.supplyCount[curse], "Curse card pile count");

	// test the victory card piles
	assertResult(startGame.supplyCount[estate], testGame.supplyCount[estate], "Estate card pile count");
	assertResult(startGame.supplyCount[duchy], testGame.supplyCount[duchy], "Duchy card pile count");
	assertResult(startGame.supplyCount[province], testGame.supplyCount[province], "Province card pile count");

	// test the kingdom card piles
	i = 0;
	while (i < 10)
	{
		char* cardStatement = malloc(sizeof(char) * (strlen(kingdomCardNames[i]) + 16 + 1));
		strcpy(cardStatement, kingdomCardNames[i]);
		strcat(cardStatement, " card pile count");
		assertResult(startGame.supplyCount[k[i]], testGame.supplyCount[k[i]], cardStatement);
		free(cardStatement);
		i++;
	}

	// test opponent's hand count increased by 1
	assertResult((startGame.handCount[nextPlayer] + 1), testGame.handCount[nextPlayer], "Opponent hand count");

	// test opponent's deck count decreased by 1
	assertResult((startGame.deckCount[nextPlayer] - 1), testGame.deckCount[nextPlayer], "Opponent deck count");

	printf("     ----- %s Card Testing Complete -----\n\n", TESTCARD);

}

