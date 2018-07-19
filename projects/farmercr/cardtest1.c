/******************************************************************************
* Program Name:	Assignment-3
* Author:			Craig Farmer
* Course:			CS 362 - 400, Summer 2018
* Date Created:	07/16/2018
* Last Modified:	07/22/2018
* Due Date:			07/22/2018
* File name:		cardtest1.c
* Description: A unit test for a dominion game function.
* Websites consulted:
*	https://www.gnu.org/software/make/manual/make.html
******************************************************************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <stdio.h>
#include "rngs.h"

#define TESTCARD "Adventurer"

void assertResult(int expected, int actual, char* testDescription)
{
	if (expected == actual)
	{
		printf("SUCCESSFULLY COMPLETED: %s; Expected: %d, Actual: %d\n", testDescription);
	}
	else
	{
		printf("FAILED: %s; Expected: %d, Actual: %d\n", testDescription, expected, actual);
	}
}

int main()
{
	int newCards = 0;
	int discarded = 1;
	int xtraCoins = 0;
	int shuffledCards = 0;

	int i, j, m;
	int handpos = 0, choice1 = 0, choice2 = 0, choice3 = 0, bonus = 0;
	int remove1, remove2;
	int seed = 50;
	int numPlayers = 2;
	int thisPlayer = 0;
	struct gameState startGame, testGame;
	int k[10] = {adventurer, baron, council_room, cutpurse, mine, minion,
		remodel, smithy, tribute, village};

	int startHandTreasure = 0;
	int testHandTreasure = 0;
	int currentCard;

	// initialize a game state and player cards
	initializeGame(numPlayers, k, seed, &testGame);

	//startGame = testGame;
	memcpy(&startGame, &testGame, sizeof(struct gameState));

	printf("----- Testing %s Card -----\n", TESTCARD);

	// 'play' the adventurer card
	cardEffect(adventurer, choice1, choice2, choice3, &testGame, handpos, &bonus);

	// test that player played 1 card
	//assertResult(startGame.playedCardCount + 1, testGame.playedCardCount, "Player played card count");
	
	// test that player gained 2 cards
	assertResult(startGame.handCount[thisPlayer] + 2 - 1, testGame.handCount[thisPlayer], "Player hand count");

	// test that player coin count is unchanged
	assertResult(startGame.coins, testGame.coins, "Player coin count");

	// test that player gained 2 treasure cards
	// count the treasure cards in the starting hand
	i = 0;
	while (i < startGame.handCount[thisPlayer])
	{
		currentCard = startGame.hand[thisPlayer][i];
		if (currentCard == copper || currentCard == silver || currentCard == gold)
		{
			startHandTreasure++;
		}
	}
	// count the treasure cards in the test hand
	i = 0;
	while (i < testGame.handCount[thisPlayer])
	{
		currentCard = testGame.hand[thisPlayer][i];
		if (currentCard == copper || currentCard == silver || currentCard == gold)
		{
			testHandTreasure++;
		}
	}
	assertResult(startHandTreasure + 2, testHandTreasure, "Player treasure card count");

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
		char* cardStatement = malloc(sizeof(char) * (strlen(k[1]) + 17 + 1));
		strcpy(cardStatement, k[i]);
		strcat(cardStatement, " card pile count");
		assertResult(startGame.supplyCount[k[i]], testGame.supplyCount[k[i]], cardStatement);
		free(cardStatement);
	}

	printf("----- %s Card Testing Complete -----\n", TESTCARD);

}
