import KafkaConsumer from '../../common-kafka/src/KafkaConsumer';

const main = async () => {

    const group = 'Order';
    const topic = { topic: 'new_order' };
    const consumerFunction = async ({ topic, partition, message }) => {
        console.log({
            value: message.value.toString(),
        })
    };
    const consumer = new KafkaConsumer(group, topic, consumerFunction);

    consumer.start();
};

main();
