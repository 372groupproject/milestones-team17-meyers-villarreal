#include <stdio.h>
#include "usloss.h"

USLOSS_Context hello;
USLOSS_Context world;

#define SIZE (USLOSS_MIN_STACK)

char stack0[SIZE];
char stack1[SIZE];

void Test0(void)
{
    for (int i = 1; i < 11; i++)
    {
        USLOSS_Console("%d Hello", i);
        USLOSS_ContextSwitch(&hello, &world);
    }

    USLOSS_Halt(0);
}

void Test1(void)
{
    for (int i = 1; i < 11; i++)
    {
        USLOSS_Console(" World");
        for (int j = 0; j < i; j++)
        {
            USLOSS_Console("%c", '!');
        }
        USLOSS_Console("\n");
        USLOSS_ContextSwitch(&world, &hello);
    }

    USLOSS_Halt(0);
}

void startup(int argc, char **argv)
{
    /*
     * Your code here. If you compile and run this as-is you
     * will get a simulator trap, which is a good opportunity
     * to verify that you get a core file and you can use it
     * with gdb.
     */

    USLOSS_ContextInit(&hello, stack0, sizeof(stack0), NULL, Test0);
    USLOSS_ContextInit(&world, stack1, sizeof(stack1), NULL, Test1);

    USLOSS_ContextSwitch(NULL, &hello);
}

// Do not modify anything below this line.

void finish(int argc, char **argv)
{
    USLOSS_Console("Goodbye.\n");
}

void test_setup(int argc, char **argv)
{
    // Do nothing.
}

void test_cleanup(int argc, char **argv)
{
    // Do nothing.
}
