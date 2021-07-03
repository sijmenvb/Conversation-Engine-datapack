# always run as the player talking with the villager (scores={lab_ape = 1})

# this function is run each tick if someone is talking to this NPC

# check if the player is in range of the npc if not end the conversation.
execute at @s unless entity @e[type=villager, distance = ..7, tag=lab_ape] run function conversation_engine:messages/lab_ape/end

# check for trigger     
execute as @s[scores={CE_trigger = 3}] run function conversation_engine:messages/lab_ape/hello2

# set trigger back to 0
scoreboard players set @s CE_trigger 0



# set the current node, do not do this here
