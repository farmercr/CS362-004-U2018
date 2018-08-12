/******************************************************************************
* Program Name:	Assignment-3
* Author:			Craig Farmer
* Course:			CS 362 - 400, Summer 2018
* Date Created:	07/16/2018
* Last Modified:	07/22/2018
* Due Date:			07/22/2018
* File name:		unittest3.c
* Description: A unit test for a dominion game function.
* Websites consulted:
*	https://www.gnu.org/software/make/manual/make.html
******************************************************************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "rngs.h"

#define TESTFUNCTION "numHandCards"
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
	//int seed = 50;
	//int numPlayers = 2;
	int thisPlayer = 0;
	struct gameState testGame;
	//int k[10] = { adventurer, baron, council_room, cutpurse, mine, minion,
	//	remodel, smithy, tribute, village };
	int expected = 0;
	int actual = 0;

	// initialize a game state and player cards
	//initializeGame(numPlayers, k, seed, &startGame);

	printf("\n     ----- Testing %s Function -----\n", TESTFUNCTION);

	int handCount = 0;
	
	/*
	int numHandCards(struct gameState *state) {
		return state->handCount[whoseTurn(state)];
	}
*/
	testGame.whoseTurn = thisPlayer;

	testGame.handCount[thisPlayer] = handCount;
	expected = handCount;
	actual = numHandCards(&testGame);
	assertResult(expected, actual, "Empty hand");

	handCount = 1;
	testGame.handCount[thisPlayer] = handCount;
	expected = handCount;
	actual = numHandCards(&testGame);
	assertResult(expected, actual, "One card in hand");

	handCount = -1;
	testGame.handCount[thisPlayer] = handCount;
	expected = handCount;
	actual = numHandCards(&testGame);
	assertResult(expected, actual, "Negative one cards in hand");

	handCount = 99;
	testGame.handCount[thisPlayer] = handCount;
	expected = handCount;
	actual = numHandCards(&testGame);
	assertResult(expected, actual, "Ninety-nine cards in hand");

	printf("     ----- %s Unit Testing Complete -----\n\n", TESTFUNCTION);

}
