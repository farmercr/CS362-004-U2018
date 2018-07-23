/******************************************************************************
* Program Name:	Assignment-3
* Author:			Craig Farmer
* Course:			CS 362 - 400, Summer 2018
* Date Created:	07/16/2018
* Last Modified:	07/22/2018
* Due Date:			07/22/2018
* File name:		unittest2.c
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

#define TESTFUNCTION "updateCoins"
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
	int thisPlayer = 0;
	struct gameState testGame;
	int expected = 0;
	int actual = 0;

	printf("\n     ----- Testing %s Function -----\n", TESTFUNCTION);

	int testCard = 0;
	int handCount = 0;
	int bonusCoins = 0;

	expected = 0;
	updateCoins(thisPlayer, &testGame, bonusCoins);
	actual = testGame.coins;
	assertResult(expected, actual, "No coin cards or bonus");
	
	bonusCoins = 5;
	expected = 5;
	updateCoins(thisPlayer, &testGame, bonusCoins);
	actual = testGame.coins;
	assertResult(expected, actual, "Five bonus coins only");

	bonusCoins = 0;
	testCard = copper;
	handCount = 1;
	testGame.hand[thisPlayer][0] = testCard;
	testGame.handCount[thisPlayer] = handCount;
	expected = 1;
	updateCoins(thisPlayer, &testGame, bonusCoins);
	actual = testGame.coins;
	assertResult(expected, actual, "One copper card in hand");

	testCard = silver;
	testGame.hand[thisPlayer][0] = testCard;
	testGame.handCount[thisPlayer] = handCount;
	expected = 2;
	updateCoins(thisPlayer, &testGame, bonusCoins);
	actual = testGame.coins;
	assertResult(expected, actual, "One silver card in hand");

	testCard = gold;
	testGame.hand[thisPlayer][0] = testCard;
	testGame.handCount[thisPlayer] = handCount;
	expected = 3;
	updateCoins(thisPlayer, &testGame, bonusCoins);
	actual = testGame.coins;
	assertResult(expected, actual, "One gold card in hand");

	testCard = silver;
	handCount = 2;
	testGame.hand[thisPlayer][1] = testCard;
	testGame.handCount[thisPlayer] = handCount;
	expected = 5;
	updateCoins(thisPlayer, &testGame, bonusCoins);
	actual = testGame.coins;
	assertResult(expected, actual, "One gold and one silver card in hand");

	bonusCoins = 0;
	testCard = copper;
	handCount = 3;
	testGame.hand[thisPlayer][2] = testCard;
	testGame.handCount[thisPlayer] = handCount;
	expected = 6;
	updateCoins(thisPlayer, &testGame, bonusCoins);
	actual = testGame.coins;
	assertResult(expected, actual, "One of each coin card in hand");

	bonusCoins = 5;
	testCard = copper;
	handCount = 3;
	testGame.hand[thisPlayer][2] = testCard;
	testGame.handCount[thisPlayer] = handCount;
	expected = 11;
	updateCoins(thisPlayer, &testGame, bonusCoins);
	actual = testGame.coins;
	assertResult(expected, actual, "One of each coin card in hand plus five bonus coins");

	printf("     ----- %s Unit Testing Complete -----\n\n", TESTFUNCTION);

}
