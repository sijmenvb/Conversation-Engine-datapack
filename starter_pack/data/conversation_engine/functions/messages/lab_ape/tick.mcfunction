#always run as the player talking with the villager (scores={lab_ape = 1})

# check if the player is in range of the npc if not end the conversation.
execute at @s unless entity @e[type=villager, distance = ..7, tag=lab_ape] run function conversation_engine:messages/lab_ape/end

# check for trigger     


# set trigger back to 0
scoreboard players set @s CE_trigger 0



# set the current node, do not do this here
