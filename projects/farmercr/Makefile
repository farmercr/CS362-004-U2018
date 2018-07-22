CFLAGS = -Wall -fpic -coverage -lm -std=c99
TEST_UNITS = unittest1 unittest2 unittest3 unittest4
TEST_CARDS = cardtest1 cardtest2 cardtest3 cardtest4
TEST_UNITS_CARDS = $(TEST_UNITS) $(TEST_CARDS)

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

playdom: dominion.o playdom.c
	gcc -o playdom playdom.c -g dominion.o rngs.o $(CFLAGS)
#To run playdom you need to enter: ./playdom <any integer number> like ./playdom 10*/
testDrawCard: testDrawCard.c dominion.o rngs.o
	gcc  -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

badTestDrawCard: badTestDrawCard.c dominion.o rngs.o
	gcc -o badTestDrawCard -g  badTestDrawCard.c dominion.o rngs.o $(CFLAGS)

testBuyCard: testDrawCard.c dominion.o rngs.o
	gcc -o testDrawCard -g  testDrawCard.c dominion.o rngs.o $(CFLAGS)

testAll: dominion.o testSuite.c
	gcc -o testSuite testSuite.c -g  dominion.o rngs.o $(CFLAGS)

interface.o: interface.h interface.c
	gcc -c interface.c -g  $(CFLAGS)

runtests: testDrawCard 
	./testDrawCard &> unittestresult.out
	gcov dominion.c >> unittestresult.out
	cat dominion.c.gcov >> unittestresult.out

player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

cardtest1: cardtest1.c dominion.c dominion.h
	gcc -o cardtest1 -g cardtest1.c dominion.c rngs.c $(CFLAGS)

cardtest2: cardtest2.c dominion.c dominion.h
	gcc -o cardtest2 -g cardtest2.c dominion.c rngs.c $(CFLAGS)

cardtest3: cardtest3.c dominion.c dominion.h
	gcc -o cardtest3 -g cardtest3.c dominion.c rngs.c $(CFLAGS)

cardtest4: cardtest4.c dominion.c dominion.h
	gcc -o cardtest4 -g cardtest4.c dominion.c rngs.c $(CFLAGS)

unittest1: unittest1.c dominion.c dominion.h
	gcc -o unittest1 -g unittest1.c dominion.c rngs.c $(CFLAGS)

unittest2: unittest2.c dominion.c dominion.h
	gcc -o unittest2 -g unittest2.c dominion.c rngs.c $(CFLAGS)

unittest3: unittest3.c dominion.c dominion.h
	gcc -o unittest3 -g unittest3.c dominion.c rngs.c $(CFLAGS)

unittest4: unittest4.c dominion.c dominion.h
	gcc -o unittest4 -g unittest4.c dominion.c rngs.c $(CFLAGS)

unittestresults.out: $(TEST_UNITS_CARDS)
	
	echo "" > unittestresults.out
	echo "" >> unittestresults.out
	echo "     -------------------- DOMINION TEST SUITE RESULTS --------------------" >> unittestresults.out
	echo "" >> unittestresults.out      
	
	echo "--------------- Testing unittest1.c ---------------" >> unittestresults.out
	./unittest1 >> unittestresults.out
	echo "     ----- unittest1.c gcov results -----" >> unittestresults.out
	gcov unittest1.c >> unittestresults.out
	cat unittest1.c.gcov >> unittestresults.out
	echo "" >> unittestresults.out
	echo "     -----dominion.c gcov results -----" >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	echo "--------------- unittest1.c complete --------------" >> unittestresults.out
	echo "" >> unittestresults.out

	echo "--------------- Testing unittest2.c ---------------" >> unittestresults.out
	./unittest2 >> unittestresults.out
	echo "     ----- unittest2.c gcov results -----" >> unittestresults.out
	gcov unittest2.c >> unittestresults.out
	cat unittest2.c.gcov >> unittestresults.out
	echo "" >> unittestresults.out
	echo "     -----dominion.c gcov results -----" >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	echo "--------------- unittest2.c complete --------------" >> unittestresults.out
	echo "" >> unittestresults.out

	echo "--------------- Testing unittest3.c ---------------" >> unittestresults.out
	./unittest3 >> unittestresults.out
	echo "     ----- unittest3.c gcov results -----" >> unittestresults.out
	gcov unittest3.c >> unittestresults.out
	cat unittest3.c.gcov >> unittestresults.out
	echo "" >> unittestresults.out
	echo "     -----dominion.c gcov results -----" >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	echo "--------------- unittest3.c complete --------------" >> unittestresults.out
	echo "" >> unittestresults.out

	echo "--------------- Testing unittest4.c ---------------" >> unittestresults.out
	./unittest4 >> unittestresults.out
		echo "     ----- unittest4.c gcov results -----" >> unittestresults.out
	gcov unittest4.c >> unittestresults.out
	cat unittest4.c.gcov >> unittestresults.out
	echo "" >> unittestresults.out
	echo "     -----dominion.c gcov results -----" >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	echo "--------------- unittest4.c complete --------------" >> unittestresults.out
	echo "" >> unittestresults.out
	
	echo "--------------- Testing cardtest1.c ---------------" >> unittestresults.out
	./cardtest1 >> unittestresults.out
	echo "     ----- cardtest1.c gcov results -----" >> unittestresults.out
	gcov cardtest1.c >> unittestresults.out
	cat cardtest1.c.gcov >> unittestresults.out
	echo "" >> unittestresults.out
	echo "     -----dominion.c gcov results -----" >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	echo "--------------- cardtest1.c complete --------------" >> unittestresults.out
	echo "" >> unittestresults.out

	echo "--------------- Testing cardtest2.c ---------------" >> unittestresults.out
	./cardtest2 >> unittestresults.out
	echo "     ----- cardtest2.c gcov results -----" >> unittestresults.out
	gcov cardtest2.c >> unittestresults.out
	cat cardtest2.c.gcov >> unittestresults.out
	echo "" >> unittestresults.out
	echo "     -----dominion.c gcov results -----" >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	echo "--------------- cardtest2.c complete --------------" >> unittestresults.out
	echo "" >> unittestresults.out

	echo "--------------- Testing cardtest3.c ---------------" >> unittestresults.out
	./cardtest3 >> unittestresults.out
	echo "     ----- cardtest3.c gcov results -----" >> unittestresults.out
	gcov cardtest3.c >> unittestresults.out
	cat cardtest3.c.gcov >> unittestresults.out
	echo "" >> unittestresults.out
	echo "     -----dominion.c gcov results -----" >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	echo "--------------- cardtest3.c complete --------------" >> unittestresults.out
	echo "" >> unittestresults.out

	echo "--------------- Testing cardtest4.c ---------------" >> unittestresults.out
	./cardtest4 >> unittestresults.out
	echo "     ----- cardtest4.c gcov results -----" >> unittestresults.out
	gcov cardtest4.c >> unittestresults.out
	cat cardtest4.c.gcov >> unittestresults.out
	echo "" >> unittestresults.out
	echo "     -----dominion.c gcov results -----" >> unittestresults.out
	gcov dominion.c >> unittestresults.out
	echo "--------------- cardtest4.c complete --------------" >> unittestresults.out
	echo "" >> unittestresults.out

	echo "--------------- dominion.c gcov results ---------------" >> unittestresults.out
	echo "" >> unittestresults.out
	##cat dominion.c.gcov >> unittestresults.out
	echo "" >> unittestresults.out

	echo "     -------------------- DOMINION TEST SUITE COMPLETE --------------------" >> unittestresults.out
	echo "" >> unittestresults.out
	echo "" >> unittestresults.out

all: playdom player 

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe unittest1 unittest2 unittest3 unittest4 cardtest1 cardtest2 cardtest3 cardtest4
