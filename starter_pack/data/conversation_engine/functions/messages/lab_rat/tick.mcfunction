# check if the player is in range of the npc if not end the conversation.
execute if entity @p[distance = 7..] run function conversation_engine:messages/lab_rat/end

# check for trigger