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
	int startHandTreasure = 0;
	int testHandTreasure = 0;
	int currentCard;

	int expected = 0;
	int actual = 0;

	// initialize a game state and player cards
	initializeGame(numPlayers, k, seed, &testGame);

	// copy the gameState to maintain a starting state for a baseline
	memcpy(&startGame, &testGame, sizeof(struct gameState));


	printf("\n     ----- Testing %s Function -----\n", TESTFUNCTION);

	expected = 10;
	actual = fullDeckCount(thisPlayer, 0, testGame);
	
	/*
	int fullDeckCount(int player, int card, struct gameState *state) {
		int i;
		int count = 0;

		for (i = 0; i < state->deckCount[player]; i++)
		{
			if (state->deck[player][i] == card) count++;
		}

		for (i = 0; i < state->handCount[player]; i++)
		{
			if (state->hand[player][i] == card) count++;
		}

		for (i = 0; i < state->discardCount[player]; i++)
		{
			if (state->discard[player][i] == card) count++;
		}

		return count;
	}

	*/


	assertResult(expected, actual, "Regular build test");
	//assertResult(2, 1, "One does not equal two");

	printf("     ----- %s Unit Testing Complete -----\n\n", TESTFUNCTION);

}
