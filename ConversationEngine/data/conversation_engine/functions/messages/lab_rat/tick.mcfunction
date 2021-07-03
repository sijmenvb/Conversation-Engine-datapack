# always run as the player talking with the villager (scores={lab_rat = 1})

# this function is run each tick if someone is talking to this NPC

# check if the player is in range of the npc if not end the conversation.
execute at @s unless entity @e[type=villager, distance = ..7, tag=lab_rat] run function conversation_engine:messages/lab_rat/end

# check for trigger     
execute as @s[scores={CE_trigger = 1}] run function conversation_engine:messages/lab_rat/hello
execute as @s[scores={CE_trigger = 2}] run function conversation_engine:messages/lab_rat/responce

# set trigger back to 0
scoreboard players set @s CE_trigger 0



# set the current node, do not do this here
