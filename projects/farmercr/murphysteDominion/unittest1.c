/******************************************************************************
* Program Name:	Assignment-3
* Author:			Craig Farmer
* Course:			CS 362 - 400, Summer 2018
* Date Created:	07/16/2018
* Last Modified:	07/22/2018
* Due Date:			07/22/2018
* File name:		unittest1.c
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

#define TESTFUNCTION "fullDeckCount"
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
	int seed = 50;
	int numPlayers = 2;
	int thisPlayer = 0;
	struct gameState startGame, testGame;
	int k[10] = { adventurer, baron, council_room, cutpurse, mine, minion,
		remodel, smithy, tribute, village };
	int expected = 0;
	int actual = 0;

	// initialize a game state and player cards
	initializeGame(numPlayers, k, seed, &startGame);

	printf("\n     ----- Testing %s Function -----\n", TESTFUNCTION);

	int testCard = copper;
	int deckCount = 0;
	int handCount = 0;
	int discardCount = 0;
	
	expected = deckCount + handCount + discardCount;
	actual = fullDeckCount(thisPlayer, testCard, &testGame);
	assertResult(expected, actual, "No cards in any deck pile");

	deckCount = 1;
	testGame.deck[thisPlayer][0] = testCard;
	testGame.deckCount[thisPlayer] = deckCount;
	expected = deckCount + handCount + discardCount;
	actual = fullDeckCount(thisPlayer, testCard, &testGame);
	assertResult(expected, actual, "One copper in deck pile");

	handCount = 1;
	testGame.hand[thisPlayer][0] = testCard;
	testGame.handCount[thisPlayer] =  handCount;
	expected = deckCount + handCount + discardCount;
	actual = fullDeckCount(thisPlayer, testCard, &testGame);
	assertResult(expected, actual, "One copper in deck and hand piles");
	
	discardCount = 1;
	testGame.discard[thisPlayer][0] = testCard;
	testGame.discardCount[thisPlayer] = discardCount;
	expected = deckCount + handCount + discardCount;
	actual = fullDeckCount(thisPlayer, testCard, &testGame);
	assertResult(expected, actual, "One copper in each pile");
	
	expected = 0;
	actual = fullDeckCount(thisPlayer, silver, &testGame);
	assertResult(expected, actual, "No silver in any pile");

	expected = 7;
	actual = fullDeckCount(thisPlayer, copper, &startGame);
	assertResult(expected, actual, "Regular start copper count");

	expected = 3;
	actual = fullDeckCount(thisPlayer, estate, &startGame);
	assertResult(expected, actual, "Regular start estate count");

	expected = 0;
	actual = fullDeckCount(thisPlayer, silver, &startGame);
	assertResult(expected, actual, "Regular start silver count");

	printf("     ----- %s Unit Testing Complete -----\n\n", TESTFUNCTION);

}
